package preisler.com.crazy_counter.hangman;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import preisler.com.crazy_counter.security.JwtTokenProvider;

@RestController
public class HangmanController {
    JwtTokenProvider jwtTokenProvider;

    public HangmanController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/hangman")
    public ResponseEntity<Integer> getWordLength(HttpServletRequest request, @RequestParam Long friendId) {
        String token = request.getHeader("Authorization");

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        String newtoken = jwtTokenProvider.generateToken(userId);

        //implement logic here


        return ResponseEntity.ok().header("Authorization", "Bearer " + newtoken).body(5);

    }


}
