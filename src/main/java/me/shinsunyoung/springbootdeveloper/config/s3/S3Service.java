package me.shinsunyoung.springbootdeveloper.config.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.hotdeal.repository.HotDealRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    private final HotDealRepository hotDealRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3Client amazonS3Client, HotDealRepository hotDealRepository) {
        this.amazonS3Client = amazonS3Client;
        this.hotDealRepository = hotDealRepository;
    }

    public String updateFiler(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3Client.putObject(bucketName, uniqueFileName, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException("s3업로드 실패",e);
        }
        return amazonS3Client.getUrl(bucketName, uniqueFileName).toString();
    }
//    hotDeal파일
    @Transactional
    public void pacthFile(Long id, List<MultipartFile> files) {
        HotDeal hotDeal = hotDealRepository.findById(id).orElse(null);
        List<Image> oldImage = hotDeal.getImages();
        for (Image image : oldImage) {
            String key = extractKeyFromUrl(image.getFileUrl());
            if (amazonS3Client.doesObjectExist(bucketName, key)) {
                amazonS3Client.deleteObject(bucketName, key);
            }
        }
        hotDeal.getImages().clear();

        for (MultipartFile file : files) {
            String fileUrl = updateFiler(file);
            Image image = new Image();
            image.setFileUrl(fileUrl);
            image.setHotDeal(hotDeal);
            hotDeal.getImages().add(image);
        }
        hotDealRepository.save(hotDeal);


    }
    private String extractKeyFromUrl(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    }


}
