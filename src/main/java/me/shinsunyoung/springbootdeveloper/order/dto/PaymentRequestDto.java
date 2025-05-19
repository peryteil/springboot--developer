package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PaymentRequestDto {
    private Long orderId;
    private Long memberId;
    private Long price;
    private List<Long> inventoryIdList; // = productManagementId 리스트
}
