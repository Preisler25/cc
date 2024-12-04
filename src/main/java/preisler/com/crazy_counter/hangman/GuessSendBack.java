package preisler.com.crazy_counter.hangman;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GuessSendBack {
    boolean inTheWord;
    List<Integer> indexes;

    public GuessSendBack(boolean inTheWord, List<Integer> indexes) {
        this.inTheWord = inTheWord;
        this.indexes = indexes;
    }
}
