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


}
