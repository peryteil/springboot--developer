package me.shinsunyoung.springbootdeveloper.order.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.dto.AdminOrderDto;
import me.shinsunyoung.springbootdeveloper.order.entity.ProductOrder;
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
                .map(order -> AdminOrderDto.builder()
                        .orderId(order.getOrderId())
                        .userEmail(order.getUser().getEmail())
                        .productName(
                                order.getOrderItems().isEmpty()
                                        ? "없음"
                                        : order.getOrderItems().get(0).getProduct().getTitle() + " 외 " + (order.getOrderItems().size() - 1) + "개"
                        )
                        .totalPrice(order.getTotalAmount())
                        .status("결제완료") // 실제 필드 있으면 getStatus()로
                        .orderedDate(null) // orderAt 필드 추가 시 수정
                        .build())
                .collect(Collectors.toList());
    }
}
