package preisler.com.crazy_counter.workout;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preisler.com.crazy_counter.security.JwtTokenProvider;

import java.util.Date;


@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final JwtTokenProvider jwtTokenProvider;

    public ExerciseController(ExerciseService exerciseService, JwtTokenProvider jwtTokenProvider) {
        this.exerciseService = exerciseService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

     @GetMapping("/getById")
     public ResponseEntity<Iterable<ExerciseEntity>> getExercisesByUserId(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        String userId = jwtTokenProvider.getUserIdFromToken(jwt);
        String NewJwt = jwtTokenProvider.generateToken(userId);
        return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(exerciseService.findByUserId(Integer.parseInt(userId)));
     }

     @GetMapping("/getByDate")
     public Iterable<ExerciseEntity> getExercisesByDate(@RequestParam Integer id, @RequestParam String date) {
         return exerciseService.findByDate(date, id);
     }

     @PostMapping("/insert")
     public ResponseEntity<Boolean> insertExercise(@RequestBody ExerciseDTO exerciseDTO, HttpServletRequest request) {
            String jwt = request.getHeader("Authorization");
            String userId = jwtTokenProvider.getUserIdFromToken(jwt);

            Date date = new Date();

            ExerciseEntity exercise = new ExerciseEntity(Integer.parseInt(userId), exerciseDTO.getName(), exerciseDTO.getReps(), exerciseDTO.getWeight(), date);
            String NewJwt = jwtTokenProvider.generateToken(userId);
            try {
                exerciseService.insertExercise(exercise);
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(true);
            } catch (Exception e) {
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(false);
            }
     }

}
