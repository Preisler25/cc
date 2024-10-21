package preisler.com.crazy_counter.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSendBack {
    private String name;
    private Long id;
    private String pfp;

    public UserSendBack() {}

    public UserSendBack(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public UserSendBack(String name, Long id, String pfp) {
        this.name = name;
        this.id = id;
        this.pfp = pfp;
    }
}

