package senior.io.taskforcheckjors.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import senior.io.taskforcheckjors.model.SendMessage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class MessageEntity extends BaseEntity{

    @NotNull
    private String title;

    private String body;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity createdBy;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receivedBy;

    @NotNull
    private Boolean seen = false;

    //galgan dto ni entityga aylantirish
    public MessageEntity(SendMessage dto) {
        this.body = dto.getBody();
        this.title = dto.getTitle();
        this.createdAt = LocalDateTime.now();
    }

}
