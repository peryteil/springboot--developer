package me.shinsunyoung.springbootdeveloper.payment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PaymentVerificationRequest {
    private String imp_uid;
    private String merchant_uid;
    private int amount;
    private List<CartItemDto> cartItems;

    @Getter
    public static class CartItemDto {
        private Long id;
        private int count;
        private ProductDto productDto;

        @Getter
        public static class ProductDto {
            private Long id;
            private String title;
            private int price;
        }
    }
}
