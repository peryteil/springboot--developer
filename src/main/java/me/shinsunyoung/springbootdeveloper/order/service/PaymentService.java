package me.shinsunyoung.springbootdeveloper.order.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.order.dto.PaymentHistoryDto;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.order.repository.OrderRepository;
import me.shinsunyoung.springbootdeveloper.order.dto.PaymentRequestDto;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import me.shinsunyoung.springbootdeveloper.order.entity.PaymentHistory;
import me.shinsunyoung.springbootdeveloper.order.entity.ProductManagement;
import me.shinsunyoung.springbootdeveloper.order.repository.PaymentRepository;
import me.shinsunyoung.springbootdeveloper.order.repository.ProductManagementRepository;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PaymentService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final ProductManagementRepository productMgtRepository;

    public void processPaymentDone(PaymentRequestDto request) {

        Long orderId = request.getOrderId();
        Long userId = request.getUserId(); // memberId → userId
        verifyUserIdMatch(userId); // 로그인 된 사용자와 요청 사용자 비교
        Long totalPrice = request.getPrice();
        List<Long> productMgtIdList = request.getInventoryIdList();

        Orders currentOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("주문 정보를 찾을 수 없습니다."));
        currentOrder.setPaymentStatus(true);

        // 사용자 정보
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("회원 정보를 찾을 수 없습니다."));

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("해당 주문서를 찾을 수 없습니다. Id : " + orderId));

        createPaymentHistory(productMgtIdList, order, user, totalPrice);
    }

    private void createPaymentHistory(List<Long> productMgtIdList, Orders order, User user, Long totalPrice) {
        for (Long productMgtId : productMgtIdList) {

            ProductManagement productMgt = productMgtRepository.findById(productMgtId)
                    .orElseThrow(() -> new NoSuchElementException("상품 정보를 찾을 수 없습니다."));

            Product product = productMgt.getProduct();
            String option = productMgt.getColor().getColor() + ", " + productMgt.getSize().toString();

            PaymentHistory paymentHistory = new PaymentHistory(
                    user, order, product, product.getName(), option, product.getPrice(), totalPrice
            );

            paymentRepository.save(paymentHistory);
        }
    }

    private void verifyUserIdMatch(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("사용자 정보가 유효하지 않습니다.");
        }
    }
    public List<PaymentHistoryDto> paymentHistoryList(Long userId) {
        return paymentRepository.findById(userId).stream()
                .map(PaymentHistoryDto::new)
                .toList();
    }
}
