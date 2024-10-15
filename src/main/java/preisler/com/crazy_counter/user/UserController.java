package preisler.com.crazy_counter.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preisler.com.crazy_counter.security.JwtTokenProvider;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserSendBack> login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        UserSendBack user = userService.login(userDTO.getName(), userDTO.getPassword());

        // Generate JWT token
        String jwtToken = jwtTokenProvider.generateToken(user.getId().toString());

        return ResponseEntity.ok().header("Authorization", "Bearer " + jwtToken).body(user);
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
