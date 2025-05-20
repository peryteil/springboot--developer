package me.shinsunyoung.springbootdeveloper.mypage.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.mypage.dto.MyPageUserDto;
import me.shinsunyoung.springbootdeveloper.mypage.dto.OrderSummaryDto;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.repository.ReviewRepository;
import me.shinsunyoung.springbootdeveloper.order.repository.OrdersRepository;
import me.shinsunyoung.springbootdeveloper.service.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final UserRepository userRepository;
    private final OrdersRepository orderRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/info")
    public MyPageUserDto getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();

        return userRepository.findById(userId)
                .map(MyPageUserDto::from)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    @GetMapping("/orders")
    public List<OrderSummaryDto> getUserOrders(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        return orderRepository.findByUser_Id(userId)
                .stream()
                .map(OrderSummaryDto::from)
                .toList();
    }

    @GetMapping("/reviews")
    public List<ReviewDto> getUserReviews(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        return reviewRepository.findByUser_Id(userId)
                .stream()
                .map(ReviewDto::from)
                .toList();
    }
}
