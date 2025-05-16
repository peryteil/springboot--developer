package me.shinsunyoung.springbootdeveloper.review.repository;

import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long id);
    List<Review> findTop5ByOrderByLikeCountDesc();
    List<Review> findTop5ByOrderByViewCountDesc();
    List<Review> findTop5ByOrderByCreatedAtDesc();

    long countByUser_Id(Long userId);
    List<Review> findByUser_Id(Long userId); // ✅ 이 줄 추가
}
