package senior.io.taskforcheckjors.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import senior.io.taskforcheckjors.entity.UserEntity;
import senior.io.taskforcheckjors.model.LoginDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResponseEntity login(LoginDto dto);

    ResponseEntity getUsers();
    //galgan zaprosdan user olish
    UserEntity getUser(HttpServletRequest request);
}
