package preisler.com.crazy_counter.emotion;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/emotions")
public class EmotionController {

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    // Endpoint to get emotions by date and userId
    @GetMapping("/byDate/{date}/{userId}")
    public List<EmotionEntity> getEmotionsByDate(@PathVariable String date, @PathVariable Integer userId) {
        return emotionService.GetEmotionByDate(date, userId);
    }

    // Endpoint to add a new emotion
    @PostMapping("/add")
    public void addEmotion(@RequestParam Integer userId, @RequestParam String emotion,
                           @RequestParam String icon, @RequestParam String date, @RequestParam Integer value) {
        emotionService.AddNewEmotion(userId, emotion, icon, date, value);
    }

    // Endpoint to delete an emotion by ID
    @DeleteMapping("/delete/{id}")
    public void deleteEmotion(@PathVariable Integer id) {
        emotionService.deleteEmotionById(id);
    }
}