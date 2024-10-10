package preisler.com.crazy_counter.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSendBack {
    private String name;
    private Long id;

    public UserSendBack() {}

    public UserSendBack(String name, Long id) {
        this.name = name;
        this.id = id;
    }

}

