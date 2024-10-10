package preisler.com.crazy_counter.user;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String name;
    private String password;

    public UserDTO() {}

        public UserDTO(String name, String password) {
            this.name = name;
            this.password = password;
        }

}

