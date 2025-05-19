package me.shinsunyoung.springbootdeveloper.order.repository;

import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    long countByUserId(Long userId); // ✅ 추가
    List<Orders> findByUserId(Long userId); // getUserOrders용도도 함께 추가 필요
}
