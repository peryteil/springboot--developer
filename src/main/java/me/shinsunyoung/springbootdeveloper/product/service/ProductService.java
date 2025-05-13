package me.shinsunyoung.springbootdeveloper.product.service;

import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Repository;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Service;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.dto.ProductDto;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final S3Service s3Service;
    private final S3Repository s3Repository;

    public ProductService(ProductRepository productRepository, S3Service s3Service, S3Repository s3Repository) {
        this.productRepository = productRepository;
        this.s3Service = s3Service;
        this.s3Repository = s3Repository;
    }
    @Transactional
    public void save(ProductDto dto, List<MultipartFile> files) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setContent(dto.getContent());
        product.setPrice(dto.getPrice());
        product.setMaterials(dto.getMaterials());
        product.setExplamationDate(dto.getExplamationDate());
        product.setWeight(dto.getWeight());
        product.setOrigin(dto.getOrigin());
        product.setStock(dto.getStock());
        product.setCreatedAt(LocalDateTime.now());

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String fileUrl = s3Service.updateFiler(file);
                Image image = new Image();
                image.setFileUrl(fileUrl);
                product.addImage(image);
                dto.getImageDtos().add(new ImageDto(fileUrl));
            }
        } else {
            System.out.printf("이미지 없이 저장됨");
        }
        productRepository.save(product);
    }
    @Transactional
    public List<ProductDto> findAllProduct() {
        return productRepository.findAll().stream().map(ProductDto::new).toList();
    }
    @Transactional
    public void updatePrduct(Long id, ProductDto dto, List<MultipartFile> files) {
        Product product = productRepository.findById(id).orElse(null);
        if (dto.getTitle() != null) {
            product.setTitle(dto.getTitle());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getMaterials() != null) {
            product.setExplamationDate(dto.getExplamationDate());
        }
        if (dto.getWeight() != null) {
            product.setOrigin(dto.getOrigin());
        }
        if (dto.getStock() != null) {
            product.setContent(dto.getContent());
        }
        if (files != null && !files.isEmpty()) {
            s3Service.patchProductFile(product.getId(), files, product);
        }
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductDto.fromEntity(product);
    }
}