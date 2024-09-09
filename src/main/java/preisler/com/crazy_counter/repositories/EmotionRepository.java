package preisler.com.crazy_counter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import preisler.com.crazy_counter.models.Emotion;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {


}
