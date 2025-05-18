package me.shinsunyoung.springbootdeveloper.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.brand.dto.BrandDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String materials;
    private String explamationDate;
    private String category;
    private String weight;
    private String origin;//원산지
    private Integer stock;
    private String content;
    private String brand;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ImageDto> imageDtos = new ArrayList<>();
    private List<ReviewDto> reviewDtos = new ArrayList<>();
    private Double averageRating;
    private Integer reviewCount;


    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.materials = product.getMaterials();
        this.explamationDate = product.getExplamationDate();
        this.weight = product.getWeight();
        this.category = product.getCategory();
        this.origin = product.getOrigin();
        this.stock = product.getStock();
        this.content = product.getContent();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        this.reviewDtos = product.getReviews().stream().map(ReviewDto::new).toList();
        this.imageDtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
    }

    public static ProductDto fromEntity(Product product) {
        ProductDto dto = new ProductDto();
        dto.id = product.getId();
        dto.title = product.getTitle();
        dto.price = product.getPrice();
        dto.materials = product.getMaterials();
        dto.explamationDate = product.getExplamationDate();
        dto.weight = product.getWeight();
        dto.category = product.getCategory();
        dto.origin = product.getOrigin();
        dto.stock = product.getStock();
        dto.content = product.getContent();
        if (product.getBrand() != null) {
            dto.brand = product.getBrand().getTitle();
        } else {
            dto.brand = "브랜드 없음";
        }
        dto.createdAt = product.getCreatedAt();
        dto.updatedAt = product.getUpdatedAt();
        dto.reviewDtos = product.getReviews().stream().map(ReviewDto::new).toList();
        dto.imageDtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        if (product.getReviews() != null && !product.getReviews().isEmpty()) {
            double avg = product.getReviews().stream().filter(r -> r.getRating() != null)
                    .mapToInt(Review::getRating).average().orElse(0.0);
            dto.averageRating = Math.round(avg * 10) / 10.0;
            dto.reviewCount = product.getReviews().size();
        } else {
            dto.averageRating = 0.0;
            dto.reviewCount = 0;

        }

        return dto;
    }
}
