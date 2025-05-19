package me.shinsunyoung.springbootdeveloper.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;

public interface ProductRepositoryV1 extends JpaRepository<Product, Long> {
    // 필요하면 메서드 선언
}