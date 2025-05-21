package me.shinsunyoung.springbootdeveloper.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import me.shinsunyoung.springbootdeveloper.order.entity.ProductOrder;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSummaryDto {

    private Long orderId;
    private String productName;
    private BigDecimal totalPrice;
    private String status;


    public static OrderSummaryDto from(ProductOrder order) {
        return OrderSummaryDto.builder()
                .orderId(order.getOrderId())
                .productName(
                        order.getOrderItems().isEmpty() ? "없음"
                                : order.getOrderItems().get(0).getProduct().getTitle()
                )
                .totalPrice(order.getTotalAmount())
                .status("결제완료") // 혹은 order.getStatus() 필드가 있다면 사용
                .build();
    }
}
