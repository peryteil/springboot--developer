package me.shinsunyoung.springbootdeveloper.mypage.dto;

import me.shinsunyoung.springbootdeveloper.review.entity.Review;

import java.time.LocalDateTime;

public record ReviewDto(Long id, String content, LocalDateTime createdAt) {
    public static ReviewDto from(Review review) {
        return new ReviewDto(review.getId(), review.getContent(), review.getCreatedAt());
    }
}
