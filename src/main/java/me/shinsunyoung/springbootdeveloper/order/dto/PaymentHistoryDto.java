package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Getter;
import me.shinsunyoung.springbootdeveloper.order.entity.PaymentHistory;

import java.time.LocalDateTime;

@Getter
public class PaymentHistoryDto {
    private String productName;
    private String productOption;
    private Integer price;
    private Long totalPrice;
    private LocalDateTime paidAt;
    private Boolean review;

    public PaymentHistoryDto(PaymentHistory entity) {
        this.productName = entity.getProductName();
        this.productOption = entity.getProductOption();
        this.price = entity.getPrice();
        this.totalPrice = entity.getTotalPrice();
        this.paidAt = entity.getPaidAt();
        this.review = entity.getReview();
    }
}
