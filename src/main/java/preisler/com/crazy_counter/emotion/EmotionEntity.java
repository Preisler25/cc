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

    private double fear;
    private double suprise;
    private double sadness;
    private double disgust;
    private double anger;
    private double anticipation;
    private double joy;
    private double trust;

    @Temporal(TemporalType.DATE)
    private Date emotion_date;

    private Long user_id;


    public EmotionEntity() {
    }

    public EmotionEntity(double fear, double suprise, double sadness, double disgust, double anger, double anticipation, double joy, double trust, Date emotion_date, Long user_id) {

        this.fear = fear;
        this.suprise = suprise;
        this.sadness = sadness;
        this.disgust = disgust;
        this.anger = anger;
        this.anticipation = anticipation;
        this.joy = joy;
        this.trust = trust;

        this.emotion_date = emotion_date;
        this.user_id = user_id;
    }


}
