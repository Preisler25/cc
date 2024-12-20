package preisler.com.crazy_counter.user;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserSendBack login(String name, String password) {
        // Find user by name
        UserEntity user = userRepository.findByName(name);

        // If user is not found, throw an exception
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        // If password is incorrect, throw an exception
        if (!encoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        return new UserSendBack(user.getName(), user.getId());
    }

    public UserSendBack register(String name, String password) {
        // If user already exists, throw an exception
        if (this.findByName(name) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        // Encode password
        password = encoder.encode(password);

        // Save user
        UserEntity user = new UserEntity(name, password);
        userRepository.save(user);

        return new UserSendBack(user.getName(), user.getId());
    }

    public UserEntity findByName(String name) {
        return userRepository.findByName(name);
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
