package preisler.com.crazy_counter.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import preisler.com.crazy_counter.user.UserRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;


    private final UserRepository userRepository;

    public FileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //get all files name in the directory
    public List<String> getAllFileNames() {
        File directory = new File(uploadDir);
        System.out.println("Directory: " + directory);
        return List.of(directory.list());
    }


    public String uploadFile(Long user_id, MultipartFile file) throws IOException {
        // Create directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //rename the file to user_id_pfp.jpg/png
        String filename = user_id + "_pfp" + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));


        // Save the file locally
        Path filePath = Paths.get(uploadDir + filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


        System.out.println("File uploaded: " + filename);
        // Saving the file URL to the database
        userRepository.updateProfilePicture(user_id, filename);
        System.out.println("File URL saved to database");

        // Return the file URL
        return "http://192.168.1.199:8080/file/" + file.getOriginalFilename();
    }

    public Resource serveFile(String filename) throws MalformedURLException {
        Path file = Paths.get(uploadDir + filename);
        return new UrlResource(file.toUri());
    }

    public boolean deleteFile(String filename) {
        Path filePath = Paths.get(uploadDir + filename);
        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            return false;
        }
    }
}
