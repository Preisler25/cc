package preisler.com.crazy_counter.hangman;

import org.springframework.stereotype.Service;
import preisler.com.crazy_counter.user.UserRepository;

import java.util.List;

@Service
public class HangmanService {
    UserRepository userRepository;
    String word;

    public HangmanService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.word = "Alma";
    }


    public GameEntity startGame(Long userId, Long friendId) {
        GameEntity gameEntity = new GameEntity(GameStatus.Guessing, "", word.length());
        return gameEntity;
    }


    public GuessSendBack ifInTheWordWhatIndex(Long userId, Long friendId, String letter) {
        boolean inTheWord = word.contains(letter);
        if (inTheWord) {

            //getting the indexes of the letter in the word
            List<Integer> indexes = new java.util.ArrayList<>();
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter.charAt(0)) {
                    indexes.add(i);
                }
            }
            return new GuessSendBack(true, indexes);
        } else {
            return new GuessSendBack(false, null);
        }
    }

    public void changeTheWord(Long userId, Long friendId, String word) {
        this.word = word;
    }



    //Logic explain
    //User1 == friendId
    //User2 == userId

    //User 1 sends a word to user 2
    //if user 2 not guess it yet, user 2 gets the word length (gameEntity.gameStatus = "guessing")
    //if user 2 guesses the word, user 2 gets the word back (gameEntity.gameStatus = "guessed")
    public void getGame(Long userId, Long friendId) {
        System.out.println("Getting game");

    }
}
