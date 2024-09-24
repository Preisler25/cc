package preisler.com.crazy_counter.emotion;

import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/emotions")
public class EmotionController {

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    // Endpoint to get emotions by date using session
    @GetMapping("/byDate")
    public List<EmotionEntity> getEmotionsByDate(@RequestParam String date, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("User is not logged in");
        }

        return emotionService.GetEmotionByDate(date, userId);
    }

    // Endpoint to add a new emotion using session
    @PostMapping("/add")
    public void addEmotion(@RequestParam String emotion, @RequestParam String icon,
                           @RequestParam String date, @RequestParam Integer value, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("User is not logged in");
        }

        emotionService.AddNewEmotion(userId, emotion, icon, date, value);
    }

    // Endpoint to delete an emotion by ID
    @DeleteMapping("/delete/{id}")
    public void deleteEmotion(@PathVariable Integer id) {
        emotionService.deleteEmotionById(id);
    }
}
