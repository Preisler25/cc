package preisler.com.crazy_counter.services;


import org.springframework.stereotype.Service;
import preisler.com.crazy_counter.models.Emotion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmotionService {
    public static List<Emotion> GetEmotionByDate(){
        List<Emotion> EmorionList = new ArrayList<>();
        EmorionList.add(new Emotion("alma", "alma", new Date(), 1));
        EmorionList.add(new Emotion("Love", "heart", new Date(),1));
        return EmorionList;
    }

    public  static void AddNewEmotion(){
        return;
    }
}
