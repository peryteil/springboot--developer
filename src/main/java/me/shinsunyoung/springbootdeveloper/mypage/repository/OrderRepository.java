package me.shinsunyoung.springbootdeveloper.mypage.repository;

import me.shinsunyoung.springbootdeveloper.mypage.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    long countByUserId(Long userId);
}
