package preisler.com.crazy_counter.satus;

public class UserSatus {
    private String name;
    private String status;

    public UserSatus() {
    }

    public UserSatus(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

}
