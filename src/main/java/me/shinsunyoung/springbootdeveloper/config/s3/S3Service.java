package me.shinsunyoung.springbootdeveloper.config.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.hotdeal.repository.HotDealRepository;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
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
            if (key !=null &&amazonS3Client.doesObjectExist(bucketName, key)) {
                amazonS3Client.deleteObject(bucketName, key);
            }
        }
        oldImage.clear();
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
        if (fileUrl == null || fileUrl.isEmpty()) {
            return null; // 혹은 throw new IllegalArgumentException("...");
        }
        int index = fileUrl.lastIndexOf("/");
        return fileUrl.substring(index + 1);
    }
    @Transactional
    public void patchProductFile(Long id, List<MultipartFile> files, Product product) {
        List<Image> oldImages = product.getImages();
        for (Image image : oldImages) {
            String key = extractKeyFromUrl(image.getFileUrl());
            if (key !=null && amazonS3Client.doesObjectExist(bucketName, key)) {
                amazonS3Client.deleteObject(bucketName, key);
            }
        }
        product.getImages().clear();
        for (MultipartFile file : files) {
            String fileUrl = updateFiler(file);
            Image image = new Image();
            image.setFileUrl(fileUrl);
            image.setProduct(product);
            product.addImage(image);
        }
    }
    @Transactional
    public void patchBrandFile(Long id, List<MultipartFile> files, Brand brand) {
        List<Image> oldImages = brand.getImages();
        for (Image image : oldImages) {
            String key = extractKeyFromUrl(image.getFileUrl());
            if (key != null && amazonS3Client.doesObjectExist(bucketName, key)) {
                amazonS3Client.deleteObject(bucketName, key);
            }
        }
        brand.getImages().clear();
        for (MultipartFile file : files) {
            String fileUrl = updateFiler(file);
            Image image = new Image();
            image.setFileUrl(fileUrl);
            image.setBrand(brand);
            brand.addImage(image);

        }
    }
}
