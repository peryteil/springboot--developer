package me.shinsunyoung.springbootdeveloper.hotdeal.repository;

import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealAllDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotDealRepository extends JpaRepository<HotDeal, Long > {
    @Query("SELECT h FROM HotDeal h ORDER BY h.createdAt DESC")
    List<HotDeal> findTop50ByOrderByCreatedAtDesc();

    @Query("SELECT new me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealAllDto(h.id, h.title, h.shopName, h.category, h.likeCount, h.viewCount, h.createdAt) " +
            "FROM HotDeal h ORDER BY h.createdAt DESC")
    List<HotDealAllDto> findTop50Dto(Pageable pageable);

    List<HotDeal> findTop4ByOrderByLikeCountDesc();

    List<HotDeal> findTop5ByOrderByLikeCountDesc();
}
