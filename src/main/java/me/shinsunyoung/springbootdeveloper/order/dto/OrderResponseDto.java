package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Getter;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;

import java.math.BigDecimal;

@Getter
public class OrderResponseDto {
    private Long orderId;
    private String merchantUid;
    private String ordererName;
    private String productName;
    private BigDecimal totalPrice;
    private Boolean paymentStatus;

    public OrderResponseDto(Orders order) {
        this.orderId = order.getOrderId();
        this.merchantUid = order.getMerchantUid();
        this.ordererName = order.getOrdererName();
        this.productName = order.getProductName();
        this.totalPrice = order.getTotalPrice();
        this.paymentStatus = order.getPaymentStatus();
    }
}
