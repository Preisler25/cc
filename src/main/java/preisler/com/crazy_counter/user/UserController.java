package preisler.com.crazy_counter.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO.getName(), userDTO.getPassword());
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO.getName(), userDTO.getPassword());
    }

    @GetMapping("/byName")
    public UserEntity getUserByName(@RequestParam String name) {
        return userService.findByName(name);
    }

    @GetMapping("/byId")
    public UserEntity getUserById(@RequestParam Long id) {
        return userService.findById(id);
    }

}
