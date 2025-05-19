package me.shinsunyoung.springbootdeveloper.order.repository;

import me.shinsunyoung.springbootdeveloper.order.entity.ProductManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductManagementRepository extends JpaRepository<ProductManagement, Long> {
}
