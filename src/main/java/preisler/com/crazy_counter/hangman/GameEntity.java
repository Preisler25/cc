package preisler.com.crazy_counter.hangman;

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
