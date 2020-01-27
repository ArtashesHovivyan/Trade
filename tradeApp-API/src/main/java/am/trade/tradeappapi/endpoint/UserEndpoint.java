package am.trade.tradeappapi.endpoint;

import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/users")
public class UserEndpoint {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserEndpoint(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
}
