package me.shinsunyoung.springbootdeveloper.review.controller;

import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.dto.ReviewResDto;
import me.shinsunyoung.springbootdeveloper.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Void> createReview(
            @PathVariable("id")Long productId,
            @RequestBody  ReviewDto dto
            ) {
        reviewService.createReview(productId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.getReviewByProductId(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateReview(
            @PathVariable("id") Long id,
            @RequestParam("content") String content,
            @RequestParam("rating") Integer rating
    ) {
        reviewService.updateReview(id, content, rating);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/top")
    public ResponseEntity<List<ReviewResDto>> getTop5Reviews() {
        List<ReviewResDto> result = reviewService.getTop5ReviewsWithImage();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/topLike")
    public ResponseEntity<List<ReviewResDto>> getTop5LikeReviews() {
        List<ReviewResDto> result = reviewService.getTop5ViewReviewsWithImage();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/latest")
    public ResponseEntity<List<ReviewResDto>> getLatest5Reviews() {
        List<ReviewResDto> result = reviewService.getLatest5ReviewsWithImage();
        return ResponseEntity.ok(result);
    }

}
