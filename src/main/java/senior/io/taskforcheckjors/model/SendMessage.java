package senior.io.taskforcheckjors.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
// Client bilan serverni ortasinda malumot almashish
public class SendMessage {
    private String title;
    private String body;
    private Long toUserId;
}
