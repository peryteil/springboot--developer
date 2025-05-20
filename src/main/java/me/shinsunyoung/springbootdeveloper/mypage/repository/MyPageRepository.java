package me.shinsunyoung.springbootdeveloper.mypage.repository;

import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyPageRepository extends JpaRepository<Orders, Long> {
//    Optional<User> findByEmail(String email);

    Long countByUser_Id(Long userId);
    List<Orders> findByUser_Id(Long userId);
}
