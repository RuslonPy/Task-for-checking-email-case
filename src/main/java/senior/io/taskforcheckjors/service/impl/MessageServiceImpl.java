package senior.io.taskforcheckjors.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senior.io.taskforcheckjors.entity.MessageEntity;
import senior.io.taskforcheckjors.entity.UserEntity;
import senior.io.taskforcheckjors.model.ResponseMessage;
import senior.io.taskforcheckjors.model.SeenEnum;
import senior.io.taskforcheckjors.model.SendMessage;
import senior.io.taskforcheckjors.repository.MessageRepository;
import senior.io.taskforcheckjors.repository.UserRepository;
import senior.io.taskforcheckjors.service.MessageService;
import senior.io.taskforcheckjors.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public List<ResponseMessage> getMessageUser(Long userId, SeenEnum seen) {

        List<MessageEntity> entities;

        if (seen == SeenEnum.ALL)
            entities = messageRepository.findAllByCreatedBy_Id(userId);
        else if (seen == SeenEnum.READ)
            entities = messageRepository.findAllByCreatedBy_IdAndSeen(userId,true);
        else
            entities = messageRepository.findAllByCreatedBy_IdAndSeen(userId,false);

        List<ResponseMessage> dtos = new ArrayList<>();
        entities.forEach(entity -> {
            dtos.add(new ResponseMessage(entity));
        });
        return dtos;
    }

    @Override
    public List<ResponseMessage> getInbox(Long id, SeenEnum seen) {
        List<MessageEntity> entities;

        if (seen == SeenEnum.ALL)
            entities = messageRepository.findAllByReceivedBy_Id(id);
        else if (seen == SeenEnum.READ)
            entities = messageRepository.findAllByReceivedBy_IdAndSeen(id,true);
        else
            entities = messageRepository.findAllByReceivedBy_IdAndSeen(id,false);

        List<ResponseMessage> dtos = new ArrayList<>();
        entities.forEach(entity -> {
            dtos.add(new ResponseMessage(entity));
            entity.setSeen(true);
        });

        messageRepository.saveAll(entities);
        return dtos;
    }

    @Override
    public ResponseMessage send(SendMessage dto, UserEntity user) {
        MessageEntity entity = new MessageEntity(dto);
        entity.setReceivedBy(userRepository.findById(dto.getToUserId()).get());
        entity.setCreatedBy(user);
        return new ResponseMessage(messageRepository.save(entity));
    }
}
