package preisler.com.crazy_counter.emotion;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import preisler.com.crazy_counter.security.JwtTokenProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emotions")
public class EmotionController {

    private final EmotionService emotionService;
    private final JwtTokenProvider jwtTokenProvider;

    public EmotionController(EmotionService emotionService , JwtTokenProvider jwtTokenProvider) {
        this.emotionService = emotionService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/byDate")
    public ResponseEntity<List<EmotionEntity>> getEmotionsByDate(@RequestParam String date, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Integer userId = Integer.parseInt(jwtTokenProvider.getUserIdFromToken(token));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        // Convert LocalDate to java.sql.Date or use LocalDate directly if your method accepts it
        Date convertedDate = java.sql.Date.valueOf(localDate);

        //return emotionService.GetEmotionByDate(convertedDate, userId);
        String NewJwt = jwtTokenProvider.generateToken(Integer.toString(userId));
        return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(emotionService.GetEmotionByDate(convertedDate, userId));
    }


    @PostMapping("/add")
    public ResponseEntity<Boolean> addEmotion(@RequestBody String emotion, @RequestBody String icon, @RequestBody Integer value,  HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        int userId =  Integer.parseInt(jwtTokenProvider.getUserIdFromToken(token));

        Date date = new Date();
        emotionService.AddNewEmotion(userId, emotion, icon, date, value);

        String NewJwt = jwtTokenProvider.generateToken(Integer.toString(userId));
        return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(true);
    }

    @DeleteMapping("/delete")
    public void deleteEmotion(@RequestParam Integer id) {
        emotionService.deleteEmotionById(id);
    }
}
