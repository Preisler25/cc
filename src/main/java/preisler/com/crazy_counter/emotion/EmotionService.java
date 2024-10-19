package preisler.com.crazy_counter.emotion;

import org.springframework.stereotype.Service;

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
    public List<EmotionEntity> GetEmotionByDate(Date date, Integer userId) {
        return (List<EmotionEntity>) emotionRepository.findByDate(date, userId);
    }

    // Insert a new emotion
    public void AddNewEmotion(int userId, String emotion, String icon, Date date, Integer value) {
        EmotionEntity emotionEntity = new EmotionEntity(emotion, icon, date, userId);
        try {
            emotionRepository.save(emotionEntity);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    // Delete an emotion by ID
    public void deleteEmotionById(Integer id) {
        emotionRepository.deleteEmotionById(id);
    }
}

