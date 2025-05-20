package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class MyOrderDto {
    private String productName;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime orderedAt;
}
