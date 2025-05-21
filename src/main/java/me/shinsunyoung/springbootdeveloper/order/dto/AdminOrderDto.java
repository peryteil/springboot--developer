package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Data
public class AdminOrderDto {
    private Long orderId;
    private String userEmail;
    private String productName;
    private BigDecimal totalPrice;
    private String status;
    private String orderedAt; // LocalDateTime이 아니라면 문자열로 처리
    private LocalDateTime orderedDate;
}
