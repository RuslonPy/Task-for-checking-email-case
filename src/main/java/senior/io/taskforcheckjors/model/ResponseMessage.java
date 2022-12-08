package senior.io.taskforcheckjors.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import senior.io.taskforcheckjors.entity.MessageEntity;

import java.time.LocalDateTime;

@Data
public class ResponseMessage {
    private String title;
    private String body;
    private String senderName;
    private String receivedName;
    private Boolean seen;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    //javob sms msg entity erkrana chiqarishda garak model
    public ResponseMessage(MessageEntity entity) {
        this.title = entity.getTitle();
        this.body = entity.getBody();
        this.senderName = entity.getCreatedBy().getUsername();
        this.receivedName = entity.getReceivedBy().getUsername();
        this.sendTime = entity.getCreatedAt();
        this.seen = entity.getSeen();
    }
}
