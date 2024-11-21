package preisler.com.crazy_counter.emotion;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import preisler.com.crazy_counter.security.JwtTokenProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        Long userId = jwtTokenProvider.getUserIdFromToken(token);

        // Update the formatter to handle the full date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        LocalDate localDate = LocalDate.parse(date, formatter);

        // Convert LocalDate to java.sql.Date or use LocalDate directly if your method accepts it
        Date convertedDate = java.sql.Date.valueOf(localDate);

        // Generate new JWT and return the emotions
        String newJwt = jwtTokenProvider.generateToken(userId);
        return ResponseEntity.ok().header("Authorization", "Bearer " + newJwt).body(emotionService.GetEmotionByDate(convertedDate, userId));
    }

    @GetMapping("/byId")
    public ResponseEntity<List<EmotionEntity>> getEmotionsByUserId(@RequestParam String FriendId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newJwt = jwtTokenProvider.generateToken(userId);

        System.out.println("Friend id: " + FriendId);
        System.out.println("User id: " + userId);

        if (!FriendId.equals("-1")) {
            return ResponseEntity.ok().header("Authorization", "Bearer " + newJwt).body(emotionService.GetEmotionByUserId(Long.parseLong(FriendId)));
        }
        return ResponseEntity.ok().header("Authorization", "Bearer " + newJwt).body(emotionService.GetEmotionByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addEmotion(@RequestBody Map<String, Map<String, String>> body, HttpServletRequest request) {
        System.out.println("add emotion");

        double fear = Double.parseDouble(body.get("data").get("fear"));
        double suprise = Double.parseDouble(body.get("data").get("suprise"));
        double sadness = Double.parseDouble(body.get("data").get("sadness"));
        double disgust = Double.parseDouble(body.get("data").get("disgust"));
        double anger = Double.parseDouble(body.get("data").get("anger"));
        double anticipation = Double.parseDouble(body.get("data").get("anticipation"));
        double joy = Double.parseDouble(body.get("data").get("joy"));
        double trust = Double.parseDouble(body.get("data").get("trust"));
        String date = body.get("data").get("emotion_date");
        Date convertedDate;

        try {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            convertedDate = java.sql.Date.valueOf(dateTime.toLocalDate());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        }


        //getting the token and from the token the user id
        String token = request.getHeader("Authorization");
        Long userId =  jwtTokenProvider.getUserIdFromToken(token);

        //creating the emotion entity
        EmotionEntity E = new EmotionEntity(fear, suprise, sadness, disgust, anger, anticipation, joy, trust, convertedDate, userId);

        //adding the emotion to the database
        emotionService.AddNewEmotion(E);

        //generating a new token
        String NewJwt = jwtTokenProvider.generateToken(userId);

        //returning the new token
        return ResponseEntity.ok().header("Authorization", "Bearer " + NewJwt).body(true);

    }

    @DeleteMapping("/delete")
    public void deleteEmotion(@RequestParam Long id) {
        emotionService.deleteEmotionById(id);
    }
}
