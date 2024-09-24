package preisler.com.crazy_counter.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        UserEntity user = userService.login(userDTO.getName(), userDTO.getPassword());

        // Store userId in session after successful login
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());

        return ResponseEntity.ok(user);
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
