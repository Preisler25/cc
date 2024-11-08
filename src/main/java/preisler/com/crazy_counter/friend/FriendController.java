package preisler.com.crazy_counter.friend;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import preisler.com.crazy_counter.security.JwtTokenProvider;
import preisler.com.crazy_counter.user.UserEntity;

import java.util.List;

@Controller
@RequestMapping("/friends")
public class FriendController {

    FriendService friendService;
    JwtTokenProvider jwtTokenProvider;

    public FriendController(JwtTokenProvider jwtTokenProvider, FriendService friendService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.friendService = friendService;
    }

    @GetMapping("/byName")
    public List<UserEntity> getFriendsByName(String name) {
        return null;
    }

    //getting friends for user by id
    @GetMapping(value = "/byId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<UserEntity>> getFriendsById(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newtoken = jwtTokenProvider.generateToken(userId);

        System.out.println("User id: " + userId);

        Iterable<UserEntity> friends = friendService.getFriendsById(userId);


        return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body((List<UserEntity>) friends);
    }


    //getting friend recommendations for user by id
    @GetMapping(value = "/suggestions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<UserEntity>> getFriendSuggestions(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newtoken = jwtTokenProvider.generateToken(userId);

        System.out.println("User id: " + userId);

        Iterable<UserEntity> friends = friendService.getFriendSuggestions(userId);

        return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body((List<UserEntity>) friends);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Boolean> addFriend(HttpServletRequest request, Long friendId) {
        try {
            String token = request.getHeader("Authorization");

            Long userId = jwtTokenProvider.getUserIdFromToken(token);
            String newtoken = jwtTokenProvider.generateToken(userId);

            System.out.println("User id: " + userId);
            System.out.println("Friend id: " + friendId);

            friendService.addFriend(userId, friendId);

            return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }

    }
    


}
