package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Builder;
import lombok.Getter;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class MyOrderDto {
    private Long id;
    private String productName;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime orderedAt;

    // ✅ 엔티티 → DTO 변환 메서드
    public static MyOrderDto fromEntity(Orders order) {
        return MyOrderDto.builder()
                .id(order.getOrderId())
                .productName(order.getProductName())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .orderedAt(order.getOrderedAt())
                .build();
    }
}
