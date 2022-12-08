package senior.io.taskforcheckjors.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senior.io.taskforcheckjors.entity.UserEntity;
import senior.io.taskforcheckjors.model.LoginDto;
import senior.io.taskforcheckjors.repository.UserRepository;
import senior.io.taskforcheckjors.security.jwt.JwtTokenProvider;
import senior.io.taskforcheckjors.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity login(LoginDto dto) {
        try {
            String username = dto.getUsername();
            UserEntity userEntity = this.userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            String token = jwtTokenProvider.createToken(username);

            Map<Object, Object> userData = new HashMap<>();
            userData.put("username", username);
            userData.put("userId", userEntity.getId());

            Map<Object, Object> model = new HashMap<>();
            model.put("userData", userData);
            model.put("token", token);

            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/match supplied");
        }
    }

    @Override
    public ResponseEntity getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Override
    public UserEntity getUser(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        UserEntity user = jwtTokenProvider.getUserEntity(token);
        return user;    }

}
