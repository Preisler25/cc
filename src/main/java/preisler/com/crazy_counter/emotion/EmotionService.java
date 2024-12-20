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
    public List<EmotionEntity> GetEmotionByDate(Date date, Long userId) {

        //covert the date to

        return (List<EmotionEntity>) emotionRepository.findByDate(date, userId);
    }

    // Insert a new emotion
    public void AddNewEmotion(EmotionEntity E) {
        try {
            emotionRepository.save(E);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve emotions by user ID
    public List<EmotionEntity> GetEmotionByUserId(Long userId) {
        return (List<EmotionEntity>) emotionRepository.findByUserId(userId);
    }


    public List<EmotionEntity> GetFriendEmotionsByLim(Long userId, int limit) {
        return (List<EmotionEntity>) emotionRepository.findFriendEmotionsByLim(userId, limit);
    }

    // Delete an emotion by ID
    public void deleteEmotionById(Long id) {
        emotionRepository.deleteEmotionById(id);
    }
}

