package me.shinsunyoung.springbootdeveloper.product.repository;

import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
