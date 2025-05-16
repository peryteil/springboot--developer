package me.shinsunyoung.springbootdeveloper.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMain {
    private Long id;
    private String title;
    private Integer price;
    private String category;
    private String origin;//원산지
    private String content;
    private String brand;
    private List<ImageDto> imageDtos = new ArrayList<>();
    private Integer reviewCount;


    public ProductMain(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.origin = product.getOrigin();
        this.content = product.getContent();
        this.imageDtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
    }

    public static ProductMain fromEntity(Product product) {
        ProductMain dto = new ProductMain();
        dto.id = product.getId();
        dto.title = product.getTitle();
        dto.price = product.getPrice();
        dto.category = product.getCategory();
        dto.origin = product.getOrigin();
        dto.content = product.getContent();
        if (product.getBrand() != null) {
            dto.brand = product.getBrand().getTitle();
        } else {
            dto.brand = "브랜드 없음";
        }
        dto.imageDtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        if (product.getReviews() != null && !product.getReviews().isEmpty()) {
            double avg = product.getReviews().stream().filter(r -> r.getRating() != null)
                    .mapToInt(Review::getRating).average().orElse(0.0);
            dto.reviewCount = product.getReviews().size();
        } else {
            dto.reviewCount = 0;

        }

        return dto;
    }
}
