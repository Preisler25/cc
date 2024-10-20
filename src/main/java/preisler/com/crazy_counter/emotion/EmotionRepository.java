package preisler.com.crazy_counter.emotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface EmotionRepository extends JpaRepository<EmotionEntity, Long> {

    // Find all emotions for a user by userId
    @Query(value = "SELECT * FROM emotions WHERE user_id = ?1", nativeQuery = true)
    Iterable<EmotionEntity> findByUserId(Integer userId);

    // Find emotions by date and userId
    @Query(value = "SELECT * FROM emotions WHERE emotion_date = ?1 AND user_id = ?2", nativeQuery = true)
    Iterable<EmotionEntity> findByDate(Date date, Integer userId);

    // Delete emotion by id
    @Query(value = "DELETE FROM emotions WHERE id = ?1", nativeQuery = true)
    void deleteEmotionById(Integer id);
}
