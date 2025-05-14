package me.shinsunyoung.springbootdeveloper.review.controller;

import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestPart("content")String content,
            @RequestPart("rating")Integer rating
            ) {
        reviewService.createReview(productId, content , rating);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.getReviewByProductId(id));
    }

//    @PatchMapping("/update/{id}")
//    public ResponseEntity<Void>
}
