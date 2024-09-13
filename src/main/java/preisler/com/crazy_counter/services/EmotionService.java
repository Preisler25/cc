package preisler.com.crazy_counter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import preisler.com.crazy_counter.models.Emotion;
import preisler.com.crazy_counter.repositories.EmotionRepository;

import java.util.List;

@Service
public class EmotionService {

    @Autowired
    private EmotionRepository emotionRepository;

    // Retrieve emotions by user ID and date
    public List<Emotion> GetEmotionByDate(String date, Integer userId) {
        return (List<Emotion>) emotionRepository.findByDate(date, userId);
    }

    // Insert a new emotion
    public void AddNewEmotion(Integer userId, String emotion, String icon, String date, Integer value) {
        emotionRepository.insertEmotion(userId, emotion, icon, date, value);
    }

    // Delete an emotion by ID
    public void deleteEmotionById(Integer id) {
        emotionRepository.deleteEmotionById(id);
    }
}
