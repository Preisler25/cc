package preisler.com.crazy_counter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users WHERE name = ?1", nativeQuery = true)
    UserEntity findByName(String name);

}
