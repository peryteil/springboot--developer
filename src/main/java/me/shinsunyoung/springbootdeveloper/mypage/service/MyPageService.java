package me.shinsunyoung.springbootdeveloper.mypage.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.mypage.dto.MyPageUserDto;
import me.shinsunyoung.springbootdeveloper.mypage.entity.Order;
import me.shinsunyoung.springbootdeveloper.mypage.repository.OrderRepository;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import me.shinsunyoung.springbootdeveloper.review.repository.ReviewRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    public MyPageUserDto getUserInfoById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

        return MyPageUserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .membership(user.getMembership())
                .role(user.getRole())
                .totalOrders(orderRepository.countByUserId(userId))
                .totalReviews(reviewRepository.countByUserId(userId))
                .totalLikes(0L)  // 좋아요 수는 추후 구현 시 변경 가능
                .build();
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Review> getUserReviews(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
