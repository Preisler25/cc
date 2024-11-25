package preisler.com.crazy_counter.file;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import preisler.com.crazy_counter.security.JwtTokenProvider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final JwtTokenProvider jwtTokenProvider;

    public FileController(FileService fileService, JwtTokenProvider jwtTokenProvider) {
        this.fileService = fileService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file, HttpServletRequest request) {
        try {
            //validate token and get user id
            String token = request.getHeader("Authorization");
            Long userId = jwtTokenProvider.getUserIdFromToken(token);
            String newToken = jwtTokenProvider.generateToken(userId);


            //calling the file service to upload the file and then save the file url
            String fileUrl = fileService.uploadFile(userId ,file);

            //returning the file url and the new token
            return ResponseEntity.ok().header("Authorization", "Bearer " + newToken).body(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Resource> serveFile(@RequestParam String filename) {
        try {
            System.out.println("file-- gett--");
            Resource resource = fileService.serveFile(filename);
            return ResponseEntity.ok().body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getAllNames")
    public ResponseEntity<String[]> getAllFileNames(HttpServletRequest request) {
        String[] fileNames = fileService.getAllFileNames();
        System.out.println(fileNames);
        return ResponseEntity.ok().body(fileNames);
    }
    


    @DeleteMapping("/del/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        boolean isDeleted = fileService.deleteFile(filename);
        if (isDeleted) {
            return ResponseEntity.ok("File deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found.");
        }
    }
}
