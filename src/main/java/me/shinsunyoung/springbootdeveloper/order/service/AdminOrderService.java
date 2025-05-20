package me.shinsunyoung.springbootdeveloper.order.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.dto.AdminOrderDto;
import me.shinsunyoung.springbootdeveloper.order.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final OrdersRepository ordersRepository;

    public List<AdminOrderDto> getAllOrders() {
        return ordersRepository.findAll()
                .stream()
                .map(orders -> AdminOrderDto.builder()
                        .orderId(orders.getOrderId())
                        .userEmail(orders.getUser().getEmail())
                        .productName(orders.getProductName())
                        .totalPrice(orders.getTotalPrice())
                        .status(orders.getStatus())
                        .orderedAt(orders.getOrderedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
