package me.shinsunyoung.springbootdeveloper.mypage.repository;

import me.shinsunyoung.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyPageRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
