package me.shinsunyoung.springbootdeveloper.review.repository;

import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long id);
}
