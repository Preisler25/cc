package preisler.com.crazy_counter.workout;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

     @GetMapping("/getById")
     public Iterable<ExerciseEntity> getExercisesByUserId(HttpServletRequest request) {
        System.out.println("------getExercisesByUserId------");

        Object sessID =  request.getSession().getAttribute("userId");
        Object sessJWT = request.getSession().getAttribute("jwtToken");

        String jwt = request.getHeader("Authorization");

         if (sessID == null || sessJWT == null) {
             return null;

         }

         int intSessID = Integer.parseInt(sessID.toString());
         System.out.println(sessID);
         System.out.println(sessJWT);
         System.out.println(jwt);

        //get userId from jwt token


        return exerciseService.findByUserId(intSessID);
     }

     @GetMapping("/getByDate")
     public Iterable<ExerciseEntity> getExercisesByDate(@RequestParam Integer id, @RequestParam String date) {
         return exerciseService.findByDate(date, id);
     }

     @PostMapping("/insert")
     public void insertExercise(@RequestBody ExerciseEntity exercise) {
         exerciseService.insertExercise(exercise);
     }

}
