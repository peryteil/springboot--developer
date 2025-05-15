package me.shinsunyoung.springbootdeveloper.mypage.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.mypage.dto.MyPageUserDto;
import me.shinsunyoung.springbootdeveloper.mypage.entity.Order;
import me.shinsunyoung.springbootdeveloper.mypage.entity.Review;
import me.shinsunyoung.springbootdeveloper.mypage.repository.OrderRepository;
import me.shinsunyoung.springbootdeveloper.mypage.repository.ReviewRepository;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
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

        return new MyPageUserDto(
                user.getNickname(),  // ✅ or user.getName()이 아니라 nickname 사용
                user.getEmail(),
                user.getMembership(),
                user.getRole(),
                orderRepository.countByUserId(userId),
                reviewRepository.countByUserId(userId),
                0 // 좋아요 수
        );
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Review> getUserReviews(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
