package me.shinsunyoung.springbootdeveloper.product.repository;

import me.shinsunyoung.springbootdeveloper.product.dto.ProductFindAll;
import me.shinsunyoung.springbootdeveloper.product.dto.ProductMain;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByBrand_TitleAndCategory(String brand, String category);
    List<Product> findByBrand_Title(String brand);
    List<Product> findByCategory(String category);
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.brand b LEFT JOIN FETCH p.images")
    List<Product> findAllSimple();
    @Query("SELECT p FROM Product p LEFT JOIN p.reviews r GROUP BY p ORDER BY COUNT(r) DESC")
    List<Product> findTop4ByReviewCount(PageRequest pageable);
    List<Product> findTop3ByCategoryAndIdNot(String category, Long id);

    @Query("SELECT p FROM Product p LEFT JOIN p.reviews r GROUP BY p.id ORDER BY COUNT(r) DESC")
    List<Product> findTopProductsByReviewCount(PageRequest pageable);
    @Query("SELECT p FROM Product p LEFT JOIN p.reviews r GROUP BY p.id ORDER BY COUNT(r) DESC")
    List<Product> findTopP5ProductsByReviewCount(PageRequest pageable);
}
