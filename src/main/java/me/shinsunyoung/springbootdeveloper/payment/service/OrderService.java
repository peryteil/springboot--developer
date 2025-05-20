package me.shinsunyoung.springbootdeveloper.payment.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    // 필요한 Repository 주입 (@Autowired 또는 생성자 주입)

    public void createOrderFromCart(List<Long> cartIds, Map payment) {
        // TODO: cartIds로 장바구니 데이터 조회
        // TODO: 주문 엔티티 생성 및 저장
        // TODO: 결제 내역(payment) 저장
        // TODO: 장바구니 비우기 또는 상태 변경

        // 프로젝트 구조에 맞게 구현하세요.
    }
}