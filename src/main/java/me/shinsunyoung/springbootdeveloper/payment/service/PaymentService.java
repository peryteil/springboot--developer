package me.shinsunyoung.springbootdeveloper.payment.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.order.dto.OrderRequestDto;
import me.shinsunyoung.springbootdeveloper.order.service.OrderService;
import me.shinsunyoung.springbootdeveloper.payment.dto.PaymentVerificationRequest;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.service.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public void verifyAndSave(PaymentVerificationRequest request, CustomUserDetails userDetails) {
        System.out.println("imp_uid: " + request.getImp_uid());
        System.out.println("amount: " + request.getAmount());

        // JWT에서 추출된 사용자 ID로 조회
        User user = userRepository.findById(userDetails.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

        // 주문 저장 DTO 생성
        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .merchantUid(request.getMerchant_uid())
                .name(request.getName())
                .tel(request.getTel())
                .addr(request.getAddr())
                .postcode(request.getPostcode())
                .amount(BigDecimal.valueOf(request.getAmount()))
                .cartItems(request.getCartItems())
                .build();

        orderService.createOrder(orderRequestDto, user);
    }

}
