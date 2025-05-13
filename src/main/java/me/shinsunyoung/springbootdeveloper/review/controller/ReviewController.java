package me.shinsunyoung.springbootdeveloper.review.controller;

import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Void> createReview(
            @PathVariable("id")Long productId,
            @RequestPart("content")String content
            ) {
        reviewService.createReview(productId, content);
        return ResponseEntity.ok().build();
    }
}
