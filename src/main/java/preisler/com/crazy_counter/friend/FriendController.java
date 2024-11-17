package preisler.com.crazy_counter.friend;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import preisler.com.crazy_counter.security.JwtTokenProvider;
import preisler.com.crazy_counter.user.UserEntity;
import preisler.com.crazy_counter.user.UserSendBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<ArrayList<UserSendBack>> getFriendSuggestions(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newtoken = jwtTokenProvider.generateToken(userId);

        System.out.println("User id: " + userId);

        ArrayList<UserSendBack> friends = friendService.getFriendSuggestions(userId);

        return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body(friends);
    }


    //getting back 1 friend by id
    @GetMapping(value = "/byFriendId", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<UserSendBack> getFriendById(HttpServletRequest request, @RequestParam Long friendId) {
        String token = request.getHeader("Authorization");

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newtoken = jwtTokenProvider.generateToken(userId);

        System.out.println("-------Sending friend back-------");

        System.out.println("User id: " + userId);
        System.out.println("Friend id: " + friendId);

        UserSendBack friend = friendService.getFriendById(userId, friendId);

        return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body(friend);
    }


    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Boolean> addFriend(HttpServletRequest request, @RequestBody Map<String, String> body) {
        String friendId = body.get("friendId");
        Long friendId1 = Long.parseLong(friendId);
        System.out.println("------Adding friend-----");
        try {
            String token = request.getHeader("Authorization");

            Long userId = jwtTokenProvider.getUserIdFromToken(token);
            String newtoken = jwtTokenProvider.generateToken(userId);

            System.out.println("User id: " + userId);
            System.out.println("Friend id: " + friendId);

            friendService.addFriend(userId, friendId1);

            return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }

    }
    

    @DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Boolean> removeFriend(HttpServletRequest request, @RequestParam Long friendId) {
        try {
            String token = request.getHeader("Authorization");

            Long userId = jwtTokenProvider.getUserIdFromToken(token);
            String newtoken = jwtTokenProvider.generateToken(userId);

            System.out.println("User id: " + userId);
            System.out.println("Friend id: " + friendId);

            friendService.removeFriend(userId, friendId);

            return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }

    }

}
