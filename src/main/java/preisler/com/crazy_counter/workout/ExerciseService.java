package preisler.com.crazy_counter.workout;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Iterable<ExerciseEntity> findByUserId(Integer  userId) {
        return exerciseRepository.findByUserId(userId);
    }

    public Iterable<ExerciseEntity> findByDate(String date, Integer userId) {
        return exerciseRepository.findByDate(date, userId);
    }

    public void insertExercise(ExerciseEntity exercise) {
        exerciseRepository.save(exercise);
    }


}
