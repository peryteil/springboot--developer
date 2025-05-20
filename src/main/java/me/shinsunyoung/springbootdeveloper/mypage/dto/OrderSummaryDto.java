package me.shinsunyoung.springbootdeveloper.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSummaryDto {

    private Long id;
    private String productName;
    private String status;

    public static OrderSummaryDto from(Orders order) {
        return OrderSummaryDto.builder()
                .id(order.getOrderId())
                .productName(order.getProductName())
                .status(order.getStatus())
                .build();
    }
}
