package me.shinsunyoung.springbootdeveloper.order.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.dto.AdminOrderDto;
import me.shinsunyoung.springbootdeveloper.order.service.AdminOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping("/orders")
    public List<AdminOrderDto> getAllOrders(){
        return adminOrderService.getAllOrders();
    }
}
