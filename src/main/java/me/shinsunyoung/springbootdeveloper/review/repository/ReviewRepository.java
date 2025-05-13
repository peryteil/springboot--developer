package me.shinsunyoung.springbootdeveloper.review.repository;

import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
