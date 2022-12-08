package senior.io.taskforcheckjors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senior.io.taskforcheckjors.entity.UserEntity;
import senior.io.taskforcheckjors.model.LoginDto;
import senior.io.taskforcheckjors.repository.UserRepository;
import senior.io.taskforcheckjors.security.jwt.JwtTokenProvider;
import senior.io.taskforcheckjors.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto dto) {
        try {
            String username = dto.getUsername();
            UserEntity userEntity = this.userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));

            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            if (!new BCryptPasswordEncoder().matches(dto.getPassword(), userEntity.getPassword())) {
                return ResponseEntity.status(401).body("Username or password incorrect");
            }

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

    @GetMapping
    public ResponseEntity getAllUsers(){
        return userService.getUsers();
    }
}
