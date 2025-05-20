package me.shinsunyoung.springbootdeveloper.mypage.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.mypage.dto.MyPageUserDto;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.review.repository.ReviewRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/{userId}/info")
    public MyPageUserDto getUserInfo(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(MyPageUserDto::from)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @GetMapping("/{userId}/orders")
    public List<OrderSummaryDto> getUserOrders(@PathVariable Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderSummaryDto::from)
                .toList();
    }

    @GetMapping("/{userId}/reviews")
    public List<ReviewDto> getUserReviews(@PathVariable Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(ReviewDto::from)
                .toList();
    }
}
