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
public class ProBest {
    private Long id;
    private String title;
    private Integer price;
    private String category;
    private String origin;//원산지
    private List<ImageDto> imageDtos = new ArrayList<>();
    private List<ReviewDto> reviewDtos = new ArrayList<>();
    private Double averageRating;
    private Integer reviewCount;


    public ProBest(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.origin = product.getOrigin();
        this.reviewDtos = product.getReviews().stream().map(ReviewDto::new).toList();
        this.imageDtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
    }

    public static ProBest fromEntity(Product product) {
        ProBest dto = new ProBest();
        dto.id = product.getId();
        dto.title = product.getTitle();
        dto.price = product.getPrice();
        dto.category = product.getCategory();
        dto.origin = product.getOrigin();
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
