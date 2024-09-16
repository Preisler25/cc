package preisler.com.crazy_counter.services;

import org.springframework.stereotype.Service;
import preisler.com.crazy_counter.models.Emotion;
import preisler.com.crazy_counter.repositories.EmotionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmotionService {

    private final EmotionRepository emotionRepository;

    // Define the date pattern used in your input
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

    public EmotionService(EmotionRepository emotionRepository) {
        this.emotionRepository = emotionRepository;
    }

    // Convert String to Date
    private Date convertStringToDate(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }

    // Retrieve emotions by user ID and date
    public List<Emotion> GetEmotionByDate(String date, Integer userId) {
        return (List<Emotion>) emotionRepository.findByDate(date, userId);
    }

    // Insert a new emotion
    public void AddNewEmotion(Integer userId, String emotion, String icon, String date, Integer value) {
        try {
            Date parsedDate = convertStringToDate(date);  // Convert String to Date
            emotionRepository.insertEmotion(userId, emotion, icon, parsedDate, value);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle error appropriately, such as logging or throwing a custom exception
        }
    }

    // Delete an emotion by ID
    public void deleteEmotionById(Integer id) {
        emotionRepository.deleteEmotionById(id);
    }
}

