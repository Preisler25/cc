package preisler.com.crazy_counter.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to get user by name
    @GetMapping("/byName/{name}")
    public UserEntity getUserByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @GetMapping("/byId/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // Endpoint to add a new user
    @PostMapping(value = "/add", consumes = "application/json")
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.insertUser(userDTO.getName(), userDTO.getPassword());
    }

}
