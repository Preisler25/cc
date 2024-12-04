package preisler.com.crazy_counter.hangman;

public class GameEntity {
    String gameStatus;
    String word;
    int wordLength;

    public GameEntity(String gameStatus, String word, int wordLength) {
        this.gameStatus = gameStatus;
        this.word = word;
        this.wordLength = wordLength;
    }

}
