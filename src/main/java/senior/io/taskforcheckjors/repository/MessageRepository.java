package senior.io.taskforcheckjors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import senior.io.taskforcheckjors.entity.MessageEntity;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    //hamma yozilgan smslani olish man yozgan maning id nomerim orqali
    List<MessageEntity> findAllByCreatedBy_Id(Long userId);
    //hamma yozilgan smslani olish man yozgan maning id nomerim orqali, oqilgan yoki oqilmaganini xaqinda
    List<MessageEntity> findAllByCreatedBy_IdAndSeen(Long userId, Boolean seen);
    // manga yubarilgan sms larni olish maning id buyicha
    List<MessageEntity> findAllByReceivedBy_Id(Long userId);
    //manga jontilgan sms larni oqilgan yoki oqilmaganini olish
    List<MessageEntity> findAllByReceivedBy_IdAndSeen(Long userId, Boolean seen);
}
