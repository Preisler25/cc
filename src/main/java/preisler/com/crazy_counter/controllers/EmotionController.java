package preisler.com.crazy_counter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import preisler.com.crazy_counter.models.Emotion;
import preisler.com.crazy_counter.services.EmotionService;

import java.util.List;

@RestController
@RequestMapping("/emotions")
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    // Endpoint to get emotions by date and userId
    @GetMapping("/byDate")
    public List<Emotion> getEmotionsByDate(@RequestParam String date, @RequestParam Integer userId) {
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
