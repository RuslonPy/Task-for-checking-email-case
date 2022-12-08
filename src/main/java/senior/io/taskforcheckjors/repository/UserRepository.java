package senior.io.taskforcheckjors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.io.taskforcheckjors.entity.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
