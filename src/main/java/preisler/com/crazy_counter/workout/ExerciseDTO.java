package preisler.com.crazy_counter.workout;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExerciseDTO {
    private  Long id;
    private String name;
    private int minutes;
    private int reps;
    private int weight;

    public ExerciseDTO() {
    }

    public ExerciseDTO(String name,int minutes, int reps, int weight) {
        this.name = name;
        this.minutes = minutes;
        this.reps = reps;
        this.weight = weight;
    }

    public ExerciseDTO(Long id, String name, int minutes, int reps, int weight) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.reps = reps;
        this.weight = weight;
    }
}
