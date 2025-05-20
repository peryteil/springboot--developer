package me.shinsunyoung.springbootdeveloper.cart.repository;

import me.shinsunyoung.springbootdeveloper.cart.entity.Cart;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByProductAndUser(Product product, User user);

    Cart findByIdAndUser(Long id, User user);

    @Query("SELECT c FROM Cart c JOIN FETCH c.product WHERE c.user.id = :userId")
    List<Cart> findByUserIdWithProduct(@Param("userId") Long userId);

    Cart findByProductIdAndUser(Long productId, User user);

    // ✅ OrderService에서 사용하기 위한 메서드 추가
    List<Cart> findByIdIn(List<Long> ids);
}
