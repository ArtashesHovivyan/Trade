package am.trade.tradeappweb.controller;

import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/log")
    public String login(@ModelAttribute User user) {
        return "login";
    }

    @PostMapping("/log")
    public String loginFilure(ModelMap modelMap) {
        modelMap.addAttribute("massage", "Username or password are invalid please try again");
        return "login";
    }

    @PostMapping("/registerSection")
    public String registerSection(@ModelAttribute User user) {
        boolean sectionByName = userService.findUserByLogin(user.getLogin());
        if (sectionByName){
            return "redirect:/registerSection";
        }
        userService.registerUser(user);
        return "mainPage";
    }

}
