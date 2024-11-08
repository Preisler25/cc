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
            friend.setPassword(null);
        }
        return friends;
    }


    //get friend suggestions by id
    public Iterable<UserEntity> getFriendSuggestions(Long userId) {
        System.out.println("Getting friend suggestions by id");
        System.out.println("User id: " + userId);
        System.out.println("Friend suggestions:");
        Iterable<UserEntity> friends = userRepository.getFriendSuggestions(userId);
        for(UserEntity friend : friends){
            System.out.println(friend.getName());
            friend.setPassword(null);
        }
        return friends;
    }

    //add friend
    public void addFriend(Long user1_id, Long user2_id) {
        System.out.println("Adding friend");
        System.out.println("User 1 id: " + user1_id);
        System.out.println("User 2 id: " + user2_id);
        userRepository.addFriend(user1_id, user2_id);
    }

    //accept friend
    public void acceptFriend(Long user1_id, Long user2_id) {
        System.out.println("Accepting friend");
        System.out.println("User 1 id: " + user1_id);
        System.out.println("User 2 id: " + user2_id);
        userRepository.acceptFriend(user1_id, user2_id);
    }

    //remove friend
    public void removeFriend(Long user1_id, Long user2_id) {
        System.out.println("Removing friend");
        System.out.println("User 1 id: " + user1_id);
        System.out.println("User 2 id: " + user2_id);
        userRepository.removeFriend(user1_id, user2_id);
    }
    public void cancelFriendRequest(Long user1_id, Long user2_id) {
        System.out.println("Cancelling friend request");
        System.out.println("User 1 id: " + user1_id);
        System.out.println("User 2 id: " + user2_id);
        userRepository.removeFriend(user1_id, user2_id);
    }
}
