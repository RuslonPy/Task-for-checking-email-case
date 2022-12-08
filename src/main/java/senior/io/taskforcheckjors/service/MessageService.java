package senior.io.taskforcheckjors.service;

import senior.io.taskforcheckjors.entity.UserEntity;
import senior.io.taskforcheckjors.model.ResponseMessage;
import senior.io.taskforcheckjors.model.SeenEnum;
import senior.io.taskforcheckjors.model.SendMessage;

import java.util.List;

public interface MessageService {
    //sms userni smslarini olish
    List<ResponseMessage> getMessageUser(Long userId, SeenEnum seen);
    // jo'natish sms
    ResponseMessage send(SendMessage dto, UserEntity user);
    //kiruvchi sms lar
    List<ResponseMessage> getInbox(Long id, SeenEnum seen);
}
