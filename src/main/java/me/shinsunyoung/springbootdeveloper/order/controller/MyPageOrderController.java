package me.shinsunyoung.springbootdeveloper.order.controller;


import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.dto.MyOrderDto;
import me.shinsunyoung.springbootdeveloper.order.service.MyPageOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageOrderController {

    private final MyPageOrderService myPageOrderService;

    @GetMapping("/{userId}/orders")
    public List<MyOrderDto> getMyOrders(@PathVariable Long userId) {
        return myPageOrderService.getOrdersForUser(userId);
    }
}
