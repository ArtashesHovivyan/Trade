package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.dto.*;
import am.trade.tradeappapi.security.CurrentUser;
import am.trade.tradeappapi.security.JwtTokenUtil;
import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.model.Role;
import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.service.RoleService;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/users")
public class UserEndpoint {

    private UserService userService;
    private final RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserEndpoint(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.roleService = roleService;
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
    public ResponseEntity getUserById(@PathVariable("id") int id) {
        try {
            System.out.println(userService.findById(id));
            return ResponseEntity.ok(userService.findById(id));
        } catch (UserNotFoundExeption e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody RegisterUserDto registerUserDto) {
        if (userService.findUserByLogin(registerUserDto.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = new User();
        user.setName(registerUserDto.getName());
        user.setSurname(registerUserDto.getSurname());
        user.setLogin(registerUserDto.getLogin());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        List<Role> roleList = new ArrayList<>();
        for (Integer roleId : registerUserDto.getRolesId()) {
            Role role = roleService.getRollById(roleId);
            roleList.add(role);
        }
        user.setRoles(roleList);
        userService.registerUser(user);
        return ResponseEntity.ok(user.getId());
    }
//Register user dto
//             {"name": "Valod",
//            "surname": "valodyan",
//            "login": "valod",
//            "password": "valod",
//            "rolesId": [1,2]}

    @PutMapping
    public ResponseEntity changeUser(@RequestBody RegisterUserDto registerUserDto) {
        User updateUser = null;
        try {
            updateUser = userService.findById(registerUserDto.getId());
        User user = new User();
        user.setId(updateUser.getId());
        user.setName(registerUserDto.getName());
        user.setSurname(registerUserDto.getSurname());
        user.setLogin(registerUserDto.getLogin());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        List<Role> roleList = new ArrayList<>();
        for (Integer roleId : registerUserDto.getRolesId()) {
            Role role = roleService.getRollById(roleId);
            roleList.add(role);
        }
        user.setRoles(roleList);
        userService.registerUser(user);
        return ResponseEntity.ok(user.getId());
        } catch (UserNotFoundExeption userNotFoundExeption) {
            userNotFoundExeption.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping ("/{changePass}")
    public ResponseEntity changePass(@RequestBody ChangeUserPasswordDto changeUserPasswordDto,
                                     @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else if (passwordEncoder.matches(changeUserPasswordDto.getOldPassword(),
                userService.getByLogin(currentUser.getUser().getLogin()).getPassword())){
            User updateUser = userService.getByLogin(currentUser.getUser().getLogin());
            User user = new User();
            user.setId(updateUser.getId());
            user.setName(currentUser.getUser().getName());
            user.setSurname(currentUser.getUser().getSurname());
            user.setLogin(currentUser.getUser().getLogin());
            user.setRoles(currentUser.getUser().getRoles());
            user.setPassword(passwordEncoder.encode(changeUserPasswordDto.getNewPassword()));
            userService.registerUser(user);
            return ResponseEntity.ok(user.getId());
        }else { return ResponseEntity.status(HttpStatus.CONFLICT).build();}
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
