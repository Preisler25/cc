package preisler.com.crazy_counter.workout;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;


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

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Transactional
    public void updateExercise(ExerciseEntity exercise) {
        exerciseRepository.save(exercise);
    }
}
