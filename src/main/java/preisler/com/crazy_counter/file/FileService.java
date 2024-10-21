package preisler.com.crazy_counter.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;



    public String uploadFile(MultipartFile file) throws IOException {
        // Create directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the file locally
        Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

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
