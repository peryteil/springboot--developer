package me.shinsunyoung.springbootdeveloper.order.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.dto.MyOrderDto;
import me.shinsunyoung.springbootdeveloper.order.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageOrderService {

    private final OrdersRepository ordersRepository;

    public List<MyOrderDto> getOrdersForUser(Long userId) {
        return ordersRepository.findByUserId(userId)
                .stream()
                .map(orders -> MyOrderDto.builder()
                        .productName(orders.getProductName())
                                .totalPrice(orders.getTotalPrice())
                                .status(orders.getStatus())
                                .orderedAt(orders.getOrderedAt())
                                .build())
                .collect(Collectors.toList());
    }
}
