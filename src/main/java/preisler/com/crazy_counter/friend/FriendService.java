package preisler.com.crazy_counter.friend;

import org.springframework.stereotype.Service;
import preisler.com.crazy_counter.user.UserEntity;
import preisler.com.crazy_counter.user.UserRepository;

@Service
public class FriendService {

    UserRepository userRepository;

    public FriendService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*friend logic
    *
    * user1 allways the one who has sent the user request
    * user 2 is the one who has received the request they can only see each other if user2 has accepted the request
    * when user 2 accepts the request the isFriend boolean is set to true
    * after that if they remove each other from friends the db should remove the row
    *
    * */

    //get friends by id
    public Iterable<UserEntity> getFriendsById(Long userId) {
        System.out.println("Getting friends by id");
        System.out.println("User id: " + userId);
        System.out.println("Friends:");
        Iterable<UserEntity> friends = userRepository.getFriendsById(userId);
        for(UserEntity friend : friends){
            System.out.println(friend.getName());
        }
        return friends;
    }
}
