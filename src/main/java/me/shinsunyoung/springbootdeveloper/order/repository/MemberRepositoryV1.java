package me.shinsunyoung.springbootdeveloper.order.repository;

import me.shinsunyoung.springbootdeveloper.order.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositoryV1 extends JpaRepository<Member, Long> {
}
