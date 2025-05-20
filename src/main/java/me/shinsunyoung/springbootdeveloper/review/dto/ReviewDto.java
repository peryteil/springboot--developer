package me.shinsunyoung.springbootdeveloper.review.dto;

import lombok.*;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;

import java.time.LocalDateTime;

@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer viewCount;
    private Integer rating;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.likeCount = review.getLikeCount();
        this.viewCount = review.getViewCount();
    }

    public static ReviewDto fromEntity(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.id = review.getId();
        dto.viewCount = review.getViewCount();
        dto.content = review.getContent();
        dto.rating = review.getRating();
        dto.createdAt = review.getCreatedAt();
        dto.updatedAt = review.getUpdatedAt();
        dto.likeCount = review.getLikeCount();
        return dto;
    }

    // ✅ 컨트롤러에서 사용하는 메서드 이름과 맞춰줌
    public static ReviewDto from(Review review) {
        return fromEntity(review); // 기존 메서드 재사용
    }
}
