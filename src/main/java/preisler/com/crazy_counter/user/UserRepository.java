package preisler.com.crazy_counter.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET pfp = ?2 WHERE id = ?1", nativeQuery = true)
    void updateProfilePicture(Long user_id, String filename);

    @Query(value =
            "SELECT * FROM users " +
                    "WHERE id IN (" +
                    "    SELECT user2_id FROM user_relations WHERE user1_id = ?1 AND user2_accepted = TRUE " +
                    "    UNION " +
                    "    SELECT user1_id FROM user_relations WHERE user2_id = ?1 AND user2_accepted = TRUE" +
                    ")",
            nativeQuery = true)
    Iterable<UserEntity> getFriendsById(Long userId);


    //checking if user is friend
    @Query(value = "SELECT EXISTS(SELECT 1 FROM user_relations WHERE user1_id = ?1 AND user2_id = ?2 AND user2_accepted = TRUE)", nativeQuery = true)
    boolean isFriend(Long user1_id, Long user2_id);


    //suggesting friends
    //the problame is that he can see users in the suggestions that he has already sent a request to but they have not accepted it
    //the solution is to add a check if the user has already sent a request to the user
    //if he has then the user should not be in the suggestions


    @Query(value =
            "SELECT * FROM users " +
                    "WHERE id NOT IN (" +
                    "    SELECT user2_id FROM user_relations WHERE user1_id = ?1" +
                    "    UNION " +
                    "    SELECT user1_id FROM user_relations WHERE user2_id = ?1" +
                    ") AND id != ?1",
            nativeQuery = true)
    Iterable<UserEntity> getFriendSuggestions(Long userId);


    //checking where user has not accepted the request
    @Query(value =
            "SELECT * FROM users " +
                    "WHERE id IN (" +
                    "    SELECT user1_id FROM user_relations WHERE user2_id = ?1 AND user2_accepted = FALSE" +
                    ") AND id != ?1",
            nativeQuery = true)
    Iterable<UserEntity> getFriendRequests(Long user2_id);

    //adding friend
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_relations (user1_id, user2_id, user2_accepted) VALUES (?1, ?2, FALSE)", nativeQuery = true)
    void addFriend(Long user1_id, Long user2_id);

    //accepting friend
    @Modifying
    @Transactional
    @Query(value = "UPDATE user_relations SET user2_accepted = TRUE WHERE user1_id = ?1 AND user2_id = ?2", nativeQuery = true)
    void acceptFriend(Long user1_id, Long user2_id);

    //removing friend
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_relations WHERE user1_id = ?1 AND user2_id = ?2", nativeQuery = true)
    void removeFriend(Long user1_id, Long user2_id);

}
