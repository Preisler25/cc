package preisler.com.crazy_counter.controllers;

import org.springframework.web.bind.annotation.*;
import preisler.com.crazy_counter.models.Emotion;
import preisler.com.crazy_counter.services.EmotionService;

import java.util.Date;
import java.util.List;

@RestController
public class EmotionController {

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public List<Emotion> GetEmotions(){
        return EmotionService.GetEmotionByDate();
    }

    @RequestMapping(value = "/set", method =  RequestMethod.POST)
    public void AddNewEmotion(){
        return;
    }
}
