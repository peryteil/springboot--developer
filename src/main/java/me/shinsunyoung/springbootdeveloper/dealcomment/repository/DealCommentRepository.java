package me.shinsunyoung.springbootdeveloper.dealcomment.repository;

import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealCommentRepository extends JpaRepository<DealComment,Long> {
    List<DealComment> findByHotDealId(Long id);
}
