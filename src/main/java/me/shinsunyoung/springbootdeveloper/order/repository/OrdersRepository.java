package me.shinsunyoung.springbootdeveloper.order.repository;

import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser_Id(Long userId);
    // 추가: 사용자 이메일 기반 주문 조회
    List<Orders> findByUser_Email(String email);

    // 추가: provider + providerId 기반 주문 조회
    List<Orders> findByUser_ProviderAndUser_ProviderId(String provider, String providerId);
}

