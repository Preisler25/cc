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


    //TODO: not to fuck up thing i will have to convert this into a Boolean sendback

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        try {
            UserSendBack user = userService.login(userDTO.getName(), userDTO.getPassword());

            // Generate JWT token
            String jwtToken = jwtTokenProvider.generateToken(user.getId());

            return ResponseEntity.ok().header("Authorization", "Bearer " + jwtToken).body(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserDTO userDTO) {
        try {
            System.out.println("trying reg");
            //giving the user data to userService to save the user.
            UserSendBack user = userService.register(userDTO.getName(), userDTO.getPassword());

            System.out.println("trying token");
            //getting the token for the user
            String token = jwtTokenProvider.generateToken(user.getId());

            System.out.println("trying sending back");
            //sending back the token for future request auth
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(true);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(false);
        }
    }
    @GetMapping("/byName")
    public UserEntity getUserByName(@RequestParam String name) {
        return userService.findByName(name);
    }

    @GetMapping("/whoAmI")
    public ResponseEntity<UserSendBack> getUserById(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newToken = jwtTokenProvider.generateToken(userId);

        UserEntity rawUser = userService.findById(userId);

        UserSendBack user = new UserSendBack(
                rawUser.getName(),
                rawUser.getId(),
                rawUser.getPfp()
        );

        return ResponseEntity.ok().header("Authorization", "Bearer " + newToken).body(user);
    }

}
