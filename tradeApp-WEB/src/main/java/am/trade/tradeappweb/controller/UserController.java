package am.trade.tradeappweb.controller;

import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.repository.UserRepository;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registerSection")
    public String registerSection() {
        return "registerSection";
    }

    @PostMapping("/registerSection")
    public String registerSection(@ModelAttribute User user) {
        boolean sectionByName = userService.findUserByLogin(user.getLogin());
        if (sectionByName){
            return "redirect:/registerSection";
        }
        userService.registerUser(user);
        return "index";
    }

}
