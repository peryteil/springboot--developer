package me.shinsunyoung.springbootdeveloper.review.service;

import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.product.repository.ProductRepository;
import me.shinsunyoung.springbootdeveloper.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository repository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public void createReview(Long productId, String content) {
        Product product = productRepository.findById(productId).orElse(null);

    }
}
