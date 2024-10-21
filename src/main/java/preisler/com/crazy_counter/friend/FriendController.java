package preisler.com.crazy_counter.friend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import preisler.com.crazy_counter.user.UserEntity;

import java.util.List;

@Controller
@RequestMapping("/friends")
public class FriendController {

    @GetMapping("/byName")
    public List<UserEntity> getFriendsByName(String name) {
        return null;
    }
}
