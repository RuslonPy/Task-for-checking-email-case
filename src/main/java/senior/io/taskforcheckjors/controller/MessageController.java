package senior.io.taskforcheckjors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.io.taskforcheckjors.model.ResponseMessage;
import senior.io.taskforcheckjors.model.SeenEnum;
import senior.io.taskforcheckjors.model.SendMessage;
import senior.io.taskforcheckjors.service.MessageService;
import senior.io.taskforcheckjors.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/send")
    public List<ResponseMessage> getMessagesUser(@RequestParam SeenEnum seen, HttpServletRequest req){
        return messageService.getMessageUser(userService.getUser(req).getId(), seen);
    }

    @GetMapping("/inbox")
    public List<ResponseMessage> getInbox(HttpServletRequest req, @RequestParam SeenEnum seen){
        return messageService.getInbox(userService.getUser(req).getId(), seen);
    }

    @PostMapping("/send")
    public ResponseMessage sendMessage(@RequestBody SendMessage dto, HttpServletRequest req){
        return messageService.send(dto, userService.getUser(req));
    }
}
