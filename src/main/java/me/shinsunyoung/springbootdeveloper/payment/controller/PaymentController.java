package me.shinsunyoung.springbootdeveloper.payment.controller;

import me.shinsunyoung.springbootdeveloper.payment.client.IamportClient;
import me.shinsunyoung.springbootdeveloper.payment.dto.PaymentVerifyRequest;
import me.shinsunyoung.springbootdeveloper.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final IamportClient iamportClient;
    private final OrderService orderService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody PaymentVerifyRequest request) {
        try {
            Map payment = iamportClient.getPaymentByImpUid(request.getImpUid());

            if (payment == null) {
                return ResponseEntity.badRequest().body("결제 정보가 존재하지 않습니다.");
            }

            Integer paidAmount = (Integer) payment.get("amount");
            String merchantUid = (String) payment.get("merchant_uid");
            String status = (String) payment.get("status");

            if (!paidAmount.equals(request.getAmount())) {
                return ResponseEntity.badRequest().body("결제 금액이 일치하지 않습니다.");
            }

            if (!merchantUid.equals(request.getMerchantUid())) {
                return ResponseEntity.badRequest().body("주문번호가 일치하지 않습니다.");
            }

            if (!"paid".equals(status)) {
                return ResponseEntity.badRequest().body("결제가 완료되지 않았습니다.");
            }

            // 주문 처리 서비스 호출 (장바구니 → 주문 생성 등)
            orderService.createOrderFromCart(request.getCartIds(), payment);

            return ResponseEntity.ok("결제 검증 및 주문 완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("결제 검증 중 오류 발생");
        }
    }
}
