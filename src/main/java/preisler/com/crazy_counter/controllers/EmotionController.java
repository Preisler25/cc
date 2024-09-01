package preisler.com.crazy_counter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionController {

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public String index() {
        return "crazy!!! :D";
    }
    
}
