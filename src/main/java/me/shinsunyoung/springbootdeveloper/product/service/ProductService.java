package me.shinsunyoung.springbootdeveloper.product.service;

import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.brand.repository.BrandRepository;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Repository;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Service;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.dto.*;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.product.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
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

        // ✅ 브랜드 처리
        if (dto.getBrand() != null && !dto.getBrand().isBlank()) {
            Brand brand = brandRepository.findByTitle(dto.getBrand().trim());
            if (brand == null) {
                brand = new Brand();
                brand.setTitle(dto.getBrand().trim());
                brand.setCreatedAt(LocalDateTime.now());

                // ✅ 필수값 기본 세팅
                brand.setFounded(0); // 예: 미입력 시 0년
                brand.setContent("내용 없음");
                brand.setOffice("미정");
                brand.setCountry("미정");
                brand.setRepresentative("미정");
                brand.setWebSite("미정");
                brand.setIntroduction("소개 없음");
                brand.setHistory("히스토리 없음");

                brandRepository.save(brand);
            }
            product.setBrand(brand);
        }

        // ✅ 이미지 처리
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String fileUrl = s3Service.updateFiler(file);
                Image image = new Image();
                image.setFileUrl(fileUrl);
                product.addImage(image);
                dto.getImageDtos().add(new ImageDto(fileUrl));
            }
        } else {
            System.out.println("이미지 없이 저장됨");
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

    public List<ProductMain> findByMain() {
        List<Product> products = productRepository.findTop4ByReviewCount(PageRequest.of(0, 4));
        return products.stream().map(x -> ProductMain.fromEntity(x)).toList();
    }

    public List<ProductRelate> findRelatedProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        List<Product> result = productRepository.findTop3ByCategoryAndIdNot(product.getCategory(), id);
        return result.stream().map(ProductRelate::fromEntity).toList();
    }

    public List<ProBest> findBestProducts() {
        List<Product> products = productRepository.findTopProductsByReviewCount(PageRequest.of(0, 20));
        return products.stream().map(ProBest::fromEntity).toList();
    }

    public List<ProductRelate> findBestpro() {
        List<Product> products = productRepository.findTopP5ProductsByReviewCount(PageRequest.of(0, 5));
        return products.stream().map(ProductRelate::fromEntity).toList();
    }
}