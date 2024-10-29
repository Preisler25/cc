package preisler.com.crazy_counter.emotion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "emotions")
public class EmotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String emoji;

    @Temporal(TemporalType.DATE)
    private Date emotion_date;

    private Long user_id;


    public EmotionEntity() {
    }

    public EmotionEntity(String name, String emoji, Date emotion_date, Long user_id) {
        this.name = name;
        this.emoji = emoji;
        this.emotion_date = emotion_date;
        this.user_id = user_id;
    }


}
