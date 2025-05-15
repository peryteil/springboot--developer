package me.shinsunyoung.springbootdeveloper.mypage.repository;

import me.shinsunyoung.springbootdeveloper.mypage.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
    long countByUserId(Long userId);
}
