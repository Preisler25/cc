package preisler.com.crazy_counter.workout;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "exercises")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int user_id;
    private String name;
    private int reps;
    private int weight;
    @Temporal(TemporalType.DATE)
    private Date work_out_date;


    public ExerciseEntity(int user_id, String name, int reps, int weight, Date work_out_date) {
        this.user_id = user_id;
        this.name = name;
        this.reps = reps;
        this.weight = weight;
        this.work_out_date = work_out_date;
    }

    public ExerciseEntity() {
    }
}
