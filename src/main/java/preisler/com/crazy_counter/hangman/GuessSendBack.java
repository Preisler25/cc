package preisler.com.crazy_counter.hangman;

import java.util.List;

public class GuessSendBack {
    boolean inTheWord;
    List<Integer> indexes;

    public GuessSendBack(boolean inTheWord, List<Integer> indexes) {
        this.inTheWord = inTheWord;
        this.indexes = indexes;
    }
}
