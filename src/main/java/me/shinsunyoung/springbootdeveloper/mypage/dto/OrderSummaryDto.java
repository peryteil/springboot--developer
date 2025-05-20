package me.shinsunyoung.springbootdeveloper.mypage.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime; // ✅ 이 줄 추가

public record OrderSummaryDto(
        Long orderId,
        String productNames,
        BigDecimal totalPrice,
        String merchantUid,
        LocalDateTime orderDay,
        String status
) {
    public static OrderSummaryDto from(Orders order) {
        return new OrderSummaryDto(
                order.getId(),
                order.getProductNames(),
                order.getTotalPrice(),
                order.getMerchantUid(),
                order.getOrderDay(),
                order.getPaymentStatus() ? "결제완료" : "결제대기"
        );
    }
}
