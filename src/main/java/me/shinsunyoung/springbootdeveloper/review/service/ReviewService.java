package me.shinsunyoung.springbootdeveloper.review.service;

import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.product.repository.ProductRepository;
import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.dto.ReviewResDto;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import me.shinsunyoung.springbootdeveloper.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository repository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public void createReview(Long productId, ReviewDto dto) {
        Product product = productRepository.findById(productId).orElse(null);
        Review review = new Review();
        review.setProduct(product);
        review.setContent(dto.getContent());
        review.setRating(dto.getRating());
        review.setCreatedAt(LocalDateTime.now());
        review.setLikeCount(dto.getLikeCount());
        review.setViewCount(dto.getViewCount());
        repository.save(review);
    }

    public List<ReviewDto> getReviewByProductId(Long id) {
        return repository.findByProductId(id).stream().map(ReviewDto::fromEntity).toList();
    }

    public void updateReview(Long id, String content, Integer rating) {
        Review review = repository.findById(id).orElse(null);
        review.setContent(content);
        review.setRating(rating);
        review.setUpdatedAt(LocalDateTime.now());
        repository.save(review);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<ReviewResDto> getTop5ReviewsWithImage() {
        List<Review> reviews = repository.findTop5ByOrderByLikeCountDesc();
        return reviews.stream().map(ReviewResDto::fromEntity).toList();

    }

    public List<ReviewResDto> getTop5ViewReviewsWithImage() {
        List<Review> reviews = repository.findTop5ByOrderByViewCountDesc();
        return reviews.stream().map(ReviewResDto::fromEntity).toList();

    }

    public List<ReviewResDto> getLatest5ReviewsWithImage() {
        List<Review> reviews = repository.findTop5ByOrderByCreatedAtDesc();
        return reviews.stream()
                .map(ReviewResDto::fromEntity).toList();
    }
}
