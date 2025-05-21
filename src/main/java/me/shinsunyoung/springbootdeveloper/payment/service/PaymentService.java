package me.shinsunyoung.springbootdeveloper.payment.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.payment.dto.PaymentVerificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void verifyAndSave(PaymentVerificationRequest request) {
        // [1] 실제로는 아임포트 서버에 imp_uid로 요청해서 결제 검증
        System.out.println("imp_uid: " + request.getImp_uid());
        System.out.println("amount: " + request.getAmount());

        // [2] 이후 DB에 저장 (Order, PaymentHistory 등)
        // 주문 정보 및 결제 상태 저장 로직 작성
    }
}
