package me.shinsunyoung.springbootdeveloper.hotdeal.repository;

import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotDealRepository extends JpaRepository<HotDeal, Long > {
}
