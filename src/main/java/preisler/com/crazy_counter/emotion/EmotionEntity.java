package preisler.com.crazy_counter.emotion;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "emotions")
public class EmotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String emoji;

    @Temporal(TemporalType.DATE)  // Use this to map Java's Date to SQL DATE
    private Date date;

    private Integer user_id;


    public EmotionEntity() {
    }

    public EmotionEntity(String name, String emoji, Date date, Integer user_id) {
        this.name = name;
        this.emoji = emoji;
        this.date = date;
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
