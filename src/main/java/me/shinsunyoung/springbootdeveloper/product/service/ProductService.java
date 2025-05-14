package me.shinsunyoung.springbootdeveloper.product.service;

import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.brand.repository.BrandRepository;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Repository;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Service;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.dto.ProductDto;
import me.shinsunyoung.springbootdeveloper.product.dto.ProductFindAll;
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
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, S3Service s3Service, S3Repository s3Repository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.s3Service = s3Service;
        this.s3Repository = s3Repository;
        this.brandRepository = brandRepository;
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
        product.setCategory(dto.getCategory());
        product.setStock(dto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        if (dto.getBrand() != null) {
            Brand brand = brandRepository.findByTitle(dto.getBrand());
            product.setBrand(brand);
        }
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
    @Transactional(readOnly = true)
    public List<ProductFindAll> findAllProduct() {
        return productRepository.findAll().stream().map(ProductFindAll::new).toList();
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

    public List<ProductDto> findByBrandAndCategory(String brand, String category) {
        List<Product> products;

        if (brand != null && category != null) {
            products = productRepository.findByBrand_TitleAndCategory(brand, category);
        } else if (brand != null) {
            products = productRepository.findByBrand_Title(brand);
        } else if (category != null) {
            products = productRepository.findByCategory(category);
        } else {
            products = productRepository.findAll();
        }
        return products.stream().map(ProductDto::fromEntity).toList();
    }
    @Transactional(readOnly = true)
    public List<ProductFindAll> findAllSimple() {
        return productRepository.findAllSimple();
    }

}