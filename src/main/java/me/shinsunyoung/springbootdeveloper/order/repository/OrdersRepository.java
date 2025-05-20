package me.shinsunyoung.springbootdeveloper.order.repository;

import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
}
