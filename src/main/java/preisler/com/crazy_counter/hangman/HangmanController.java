package preisler.com.crazy_counter.hangman;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preisler.com.crazy_counter.security.JwtTokenProvider;

import java.util.Map;

@RestController
@RequestMapping("/hangman")
public class HangmanController {
    HangmanService hangmanService;
    JwtTokenProvider jwtTokenProvider;

    public HangmanController(JwtTokenProvider jwtTokenProvider, HangmanService hangmanService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.hangmanService = hangmanService;
    }

    @GetMapping(value = "/start", produces = "application/json")
    public ResponseEntity<GameEntity> startGame() {
        System.out.println("Starting game");
        GameEntity gameEntity = hangmanService.startGame(Long.parseLong("1"), Long.parseLong("2"));
        System.out.println(gameEntity);
        return ResponseEntity.ok().body(gameEntity);
    }

    @GetMapping("/guess")
    public ResponseEntity<GuessSendBack> guess(HttpServletRequest request, @RequestParam String letter) {
        GuessSendBack guessSendBack = hangmanService.ifInTheWordWhatIndex(Long.parseLong("1"), Long.parseLong("2"), letter);

        return ResponseEntity.ok().body(guessSendBack);
    }

    @PutMapping("/change")
    public void changeTheWord(HttpServletRequest request, @RequestBody Map<String, String> word) {
        hangmanService.changeTheWord(Long.parseLong("1"), Long.parseLong("2"), word.get("word"));
    }



    @GetMapping("/hangman")
    public ResponseEntity<GameEntity> startGame(HttpServletRequest request, @RequestParam Long friendId) {
        String token = request.getHeader("Authorization");

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newtoken = jwtTokenProvider.generateToken(userId);

        System.out.println("User id: " + userId);

        GameEntity gameEntity = hangmanService.startGame(userId, friendId);

        return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body(gameEntity);
    }


}
