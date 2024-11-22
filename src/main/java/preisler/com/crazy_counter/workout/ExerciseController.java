package preisler.com.crazy_counter.workout;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preisler.com.crazy_counter.security.JwtTokenProvider;

import java.text.SimpleDateFormat;
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
        Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
        String NewJwt = jwtTokenProvider.generateToken(userId);
        return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(exerciseService.findByUserId(userId));
     }

     @GetMapping("/getByDate")
     public Iterable<ExerciseEntity> getExercisesByDate(@RequestParam Long id, @RequestParam String date) {
         return exerciseService.findByDate(date, id);
     }

     @DeleteMapping("/delete")
        public ResponseEntity<Boolean> deleteExercise(@RequestParam Long id, HttpServletRequest request) {
            String jwt = request.getHeader("Authorization");
            Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
            String NewJwt = jwtTokenProvider.generateToken(userId);
            try {
                exerciseService.deleteExercise(id);
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(true);
            } catch (Exception e) {
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(false);
            }
        }
        @PutMapping("/update")
        public ResponseEntity<Boolean> updateExercise(@RequestBody ExerciseDTO exerciseDTO, HttpServletRequest request) {

            System.out.println("---------Update: ");
            System.out.println("---------ExerciseDTO: ");
            System.out.println(exerciseDTO.getReps());

            String jwt = request.getHeader("Authorization");
            Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
            String NewJwt = jwtTokenProvider.generateToken(userId);


            Date date = new Date();

            try {
                ExerciseEntity exercise = new ExerciseEntity(userId , exerciseDTO.getId() , exerciseDTO.getName(),exerciseDTO.getMinutes() ,  exerciseDTO.getReps(), exerciseDTO.getWeight(), date);
                exerciseService.updateExercise(exercise);
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(true);
            } catch (Exception e) {
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(false);
            }
        }

     @PostMapping("/insert")
     public ResponseEntity<Boolean> insertExercise(@RequestBody ExerciseDTO exerciseDTO, HttpServletRequest request) {
            String jwt = request.getHeader("Authorization");
            Long userId = jwtTokenProvider.getUserIdFromToken(jwt);

            Date date = new Date();

            ExerciseEntity exercise = new ExerciseEntity(userId, exerciseDTO.getName(), exerciseDTO.getReps(), exerciseDTO.getMinutes(), exerciseDTO.getWeight(), date);
            String NewJwt = jwtTokenProvider.generateToken(userId);
            try {
                exerciseService.insertExercise(exercise);
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(true);
            } catch (Exception e) {
                return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(false);
            }
     }

}
