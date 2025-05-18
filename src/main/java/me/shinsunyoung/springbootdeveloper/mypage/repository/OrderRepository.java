package me.shinsunyoung.springbootdeveloper.mypage.repository;

import me.shinsunyoung.springbootdeveloper.mypage.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<MyOrder, Long> {
    List<MyOrder> findByUserId(Long userId);
    long countByUserId(Long userId);
}
