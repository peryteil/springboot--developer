package me.shinsunyoung.springbootdeveloper.payment.contoroller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.payment.dto.PaymentVerificationRequest;
import me.shinsunyoung.springbootdeveloper.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/verify")
    public void verifyAndSavePayment(@RequestBody PaymentVerificationRequest request) {
        paymentService.verifyAndSave(request);
    }
}