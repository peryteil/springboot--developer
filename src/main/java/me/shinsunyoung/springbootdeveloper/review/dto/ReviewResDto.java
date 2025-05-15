package me.shinsunyoung.springbootdeveloper.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Integer rating;
    private Integer likeCount;
    private Integer viewCount;
    private List<String> imageUrl;
    private String productName;
    private Long productId;

    public ReviewResDto(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.rating = review.getRating();
        this.likeCount = review.getLikeCount();
        this.viewCount = review.getViewCount();
        if (review.getProduct() != null && review.getProduct().getImages() != null &&
                !review.getProduct().getImages().isEmpty()) {
            this.imageUrl = review.getProduct().getImages().stream().map(x -> x.getFileUrl()).toList();

        }else {
            this.imageUrl = null;
        }


    }

    public static ReviewResDto fromEntity(Review review) {
        ReviewResDto dto = new ReviewResDto();
        dto.id = review.getId();
        dto.content = review.getContent();
        dto.createdAt = review.getCreatedAt();
        dto.rating = review.getRating();
        dto.likeCount = review.getLikeCount();
        dto.viewCount = review.getViewCount();
        if (review.getProduct() != null) {
            dto.productName = review.getProduct().getTitle();
            dto.productId = review.getProduct().getId();
            if (review.getProduct().getImages() != null && !review.getProduct().getImages().isEmpty()) {
                dto.imageUrl = review.getProduct().getImages().stream()
                        .map(Image::getFileUrl)
                        .toList();
            } else {
                dto.imageUrl = null;
            }
        } else {
            dto.productId = null;
            dto.productName = null;
            dto.imageUrl = null;
        }
        return dto;
    }
}
