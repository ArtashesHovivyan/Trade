package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.dto.AuthenticationRequest;
import am.trade.tradeappapi.dto.AuthenticationResponse;
import am.trade.tradeappapi.dto.UserDto;
import am.trade.tradeappapi.security.JwtTokenUtil;
import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserEndpoint {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserEndpoint(UserService userService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = null;
        user = userService.getByLogin(authenticationRequest.getLogin());
        if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getLogin());
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(token)
                    .userDto(UserDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .login(user.getLogin())
                            .build())
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping
    public List<User> users() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity user(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (UserNotFoundExeption e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        if (userService.findUserByLogin(user.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerUser(user);
        return ResponseEntity.ok(user.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id) {
        try {
            userService.findById(id);
        } catch (UserNotFoundExeption e) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
