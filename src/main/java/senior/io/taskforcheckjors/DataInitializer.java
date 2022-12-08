package senior.io.taskforcheckjors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import senior.io.taskforcheckjors.entity.UserEntity;
import senior.io.taskforcheckjors.repository.UserRepository;

import java.util.ArrayList;

import java.util.List;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<UserEntity> users = new ArrayList<>();
        users.add(UserEntity.builder()
                .username("lennon")
                .password(this.passwordEncoder.encode("wgcbxz"))
                .build());
        users.add(UserEntity.builder()
                .username("harrison")
                .password(this.passwordEncoder.encode("oxbqge"))
                .build());
        users.add(UserEntity.builder()
                .username("starr")
                .password(this.passwordEncoder.encode("apbqyy"))
                .build());
        if (userRepository.count() == 0)
            userRepository.saveAll(users);
    }
}
