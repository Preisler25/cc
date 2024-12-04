package preisler.com.crazy_counter.hangman;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameEntity {
    GameStatus gameStatus;
    String word;
    int wordLength;

    public GameEntity(GameStatus gameStatus, String word, int wordLength) {
        this.gameStatus = gameStatus;
        this.word = word;
        this.wordLength = wordLength;
    }

}
