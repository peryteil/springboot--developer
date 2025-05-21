package me.shinsunyoung.springbootdeveloper.order.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.dto.MyOrderDto;
import me.shinsunyoung.springbootdeveloper.order.entity.ProductOrder;
import me.shinsunyoung.springbootdeveloper.order.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageOrderService {

    private final OrdersRepository ordersRepository;

    public List<MyOrderDto> getOrdersForUser(Long userId) {
        return ordersRepository.findByUser_Id(userId)
                .stream()
                .map(order -> MyOrderDto.builder()
                        .productName(
                                order.getOrderItems().isEmpty()
                                        ? "없음"
                                        : order.getOrderItems().get(0).getProduct().getTitle() +
                                        (order.getOrderItems().size() > 1 ? " 외 " + (order.getOrderItems().size() - 1) + "개" : "")
                        )
                        .totalPrice(order.getTotalAmount())
                        .status("결제완료") // 실제 status 필드 추가했다면 order.getStatus()
                        .orderedAt(null)  // orderedAt 필드 추가 시 order.getOrderedAt()
                        .build())
                .collect(Collectors.toList());
    }
}
