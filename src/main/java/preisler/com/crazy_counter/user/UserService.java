package preisler.com.crazy_counter.user;


import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByName(String name) {
        return userRepository.findByName(name);
    }

    public  UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void insertUser(String name, String password) {
        if (this.findByName(name) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        UserEntity user = new UserEntity(name, password);
        userRepository.save(user);
    }
}
