package preisler.com.crazy_counter.workout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {

    @Query(value = "SELECT * FROM exercises WHERE user_id = ?1 ORDER BY work_out_date DESC", nativeQuery = true)
    Iterable<ExerciseEntity> findByUserId(Long userId);

    @Query(value = "SELECT * FROM exercises WHERE work_out_date = ?1 AND user_id = ?2 ORDER BY work_out_date DESC", nativeQuery = true)
    Iterable<ExerciseEntity> findByDate(String date, Long userId);
}
