package me.shinsunyoung.springbootdeveloper.config.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    private final AmazonS3Client amazonS3Client;
    private final S3Service s3Service;

    public FileController(AmazonS3Client amazonS3Client, S3Service s3Service) {
        this.amazonS3Client = amazonS3Client;
        this.s3Service = s3Service;
    }

    @PostMapping("/update")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = s3Service.updateFiler(file);
        return ResponseEntity.ok(fileUrl);
    }

}
