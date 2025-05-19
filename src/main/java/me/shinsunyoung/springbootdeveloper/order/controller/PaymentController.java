package me.shinsunyoung.springbootdeveloper.order.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shinsunyoung.springbootdeveloper.cart.entity.Cart;
import me.shinsunyoung.springbootdeveloper.cart.repository.CartRepository;
import me.shinsunyoung.springbootdeveloper.order.repository.OrderRepository;
import me.shinsunyoung.springbootdeveloper.order.dto.PaymentHistoryDto;
import me.shinsunyoung.springbootdeveloper.order.dto.PaymentRequestDto;
import me.shinsunyoung.springbootdeveloper.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final HttpSession httpSession;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final CartRepository cartRepository;

    private IamportClient iamportClient;

    @Value("${IMP_API_KEY}")
    private String apiKey;

    @Value("${IMP_API_SECRET}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    /**
     * 아임포트 결제 검증 및 DB 처리
     */
    @PostMapping("/order/payment/{imp_uid}")
    public IamportResponse<Payment> validateIamport(
            @PathVariable String imp_uid,
            @RequestBody PaymentRequestDto request
    ) throws IamportResponseException, IOException {

        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("결제 요청 응답. 주문 번호: {}", payment.getResponse().getMerchantUid());

        paymentService.processPaymentDone(request);

        return payment;
    }

    /**
     * 결제 완료 후 장바구니 및 세션 삭제
     */
    @GetMapping("/order/paymentconfirm")
    public void deleteSession() {
        List<Long> cartIds = (List<Long>) httpSession.getAttribute("cartIds");

        for (Long cartId : cartIds) {
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new NoSuchElementException("삭제할 장바구니를 찾을 수 없습니다."));
            cartRepository.delete(cart);
        }

        httpSession.removeAttribute("temporaryOrder");
        httpSession.removeAttribute("cartIds");
    }

    /**
     * 결제 내역 조회
     */
    @GetMapping("/paymenthistory/{memberId}")
    public ResponseEntity<List<PaymentHistoryDto>> paymentList(@PathVariable Long memberId) {
        List<PaymentHistoryDto> historyList = paymentService.paymentHistoryList(memberId);
        return ResponseEntity.ok(historyList);
    }
}
