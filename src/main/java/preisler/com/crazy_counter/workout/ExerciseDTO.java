package preisler.com.crazy_counter.workout;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExerciseDTO {
    private String name;
    private int reps;
    private int weight;

    public ExerciseDTO() {
    }

    public ExerciseDTO(String name, int reps, int weight) {
        this.name = name;
        this.reps = reps;
        this.weight = weight;
    }
}
