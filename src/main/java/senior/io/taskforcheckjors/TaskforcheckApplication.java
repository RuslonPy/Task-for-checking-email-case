package senior.io.taskforcheckjors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing
@EnableWebMvc
public class TaskforcheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskforcheckApplication.class, args);
	}


	@Bean
	public PasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
}
