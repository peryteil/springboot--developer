package me.shinsunyoung.springbootdeveloper.review.repository;

import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long id);
    List<Review> findTop5ByOrderByLikeCountDesc();
    List<Review> findTop5ByOrderByViewCountDesc();
    List<Review> findTop5ByOrderByCreatedAtDesc();

    long countByUserId(Long userId); // ✅ 사용 중인 메서드
    List<Review> findByUserId(Long userId); // ✅ 추가해줘야 할 메서드
}
