package me.shinsunyoung.springbootdeveloper.mypage.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.config.jwt.TokenProvider;
import me.shinsunyoung.springbootdeveloper.mypage.dto.MyPageUserDto;
import me.shinsunyoung.springbootdeveloper.mypage.entity.Order;
import me.shinsunyoung.springbootdeveloper.mypage.service.MyPageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    private final TokenProvider tokenProvider;

    @GetMapping("/user")
    public MyPageUserDto getUser(@RequestHeader("Authorization") String authHeader) {
        Long userId = tokenProvider.getUserId(extractToken(authHeader));
        return myPageService.getUserInfoById(userId);
    }

    @GetMapping("/orders")
    public List<Order> getOrders(@RequestHeader("Authorization") String authHeader) {
        Long userId = tokenProvider.getUserId(extractToken(authHeader));
        return myPageService.getUserOrders(userId);
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
