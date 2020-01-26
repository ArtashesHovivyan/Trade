package am.trade.tradeappapi.endpoint;

import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/users")
public class UserEndpoint {

    private UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
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

}
