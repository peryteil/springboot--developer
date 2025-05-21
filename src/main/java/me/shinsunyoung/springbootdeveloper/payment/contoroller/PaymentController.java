package me.shinsunyoung.springbootdeveloper.payment.contoroller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.payment.dto.PaymentVerificationRequest;
import me.shinsunyoung.springbootdeveloper.payment.service.PaymentService;
import me.shinsunyoung.springbootdeveloper.service.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/verify")
    public void verifyAndSavePayment(
            @RequestBody PaymentVerificationRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails // JWT 토큰에서 유저 정보 추출
    ) {
        paymentService.verifyAndSave(request, userDetails);
    }
}