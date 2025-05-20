package me.shinsunyoung.springbootdeveloper.mypage.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.config.jwt.TokenProvider;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.mypage.dto.MyOrderDto;
import me.shinsunyoung.springbootdeveloper.mypage.dto.MyPageUserDto;
import me.shinsunyoung.springbootdeveloper.mypage.service.MyPageService;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    @GetMapping("/user")
    public MyPageUserDto getUser(@RequestHeader("Authorization") String authHeader) {
        Long userId = tokenProvider.getUserId(extractToken(authHeader));
        User user = userService.findById(userId); // ✅ 이메일 로그인 사용자도 정보 가져오도록 보장

        return MyPageUserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .membership(user.getMembership())
                .role(user.getRole())
                .build();
    }

    @GetMapping("/orders")
    public List<MyOrderDto> getOrders(@RequestHeader("Authorization") String authHeader) {
        Long userId = tokenProvider.getUserId(extractToken(authHeader));
        List<Orders> orders = myPageService.getUserOrders(userId);

        return orders.stream()
                .map(order -> new MyOrderDto(
                        order.getOrderId(),
                        order.getProductName(),
                        order.getTotalPrice(),
                        order.getOrderDay()
                ))
                .toList();
    }

    @GetMapping("/reviews")
    public List<Review> getReviews(@RequestHeader("Authorization") String authHeader) {
        Long userId = tokenProvider.getUserId(extractToken(authHeader));
        return myPageService.getUserReviews(userId);
    }

    private String extractToken(String header) {
        return header.replace("Bearer ", "");
    }
}
