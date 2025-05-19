package me.shinsunyoung.springbootdeveloper.cart.service;

import me.shinsunyoung.springbootdeveloper.cart.dto.CartDto;
import me.shinsunyoung.springbootdeveloper.cart.entity.Cart;
import me.shinsunyoung.springbootdeveloper.cart.repository.CartRepository;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.product.repository.ProductRepository;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.service.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public void addCart(Long id, Long userId, CartDto dto) {
        Product product = productRepository.findById(id).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Cart cart = cartRepository.findByProductAndUser(product, user);
        if (product == null || user == null) {
            throw new IllegalArgumentException("상품 또는 사용자가 존재하지 않습니다.");
        }
        if (cart != null) {
            cart.setCount(cart.getCount() + dto.getCount());
            cart.setPrice(product.getPrice() * cart.getCount());
        } else {
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setPrice(product.getPrice() * dto.getCount());
            cart.setCount(dto.getCount());

        }
        cartRepository.save(cart);
    }

    public void deleteByCartId(Long id, User user) {
        Cart cart = cartRepository.findByIdAndUser(id, user);
        if (cart == null) {
            throw new IllegalArgumentException("장바구니 항목이 존재하지 않거나 사용자 정보가 일치하지 않습니다");
        }
        cartRepository.delete(cart);

    }

    public List<CartDto> findByUserIdWithProduct(Long userId) {
        List<Cart> carts = cartRepository.findByUserIdWithProduct(userId);
        return carts.stream().map(CartDto::fromEntity).toList();
    }

    public void updateById(Long productId, User user, Integer status) {
        Cart cart = cartRepository.findByProductIdAndUser(productId, user);
        if (status > 0) {
            cart.setCount(cart.getCount() + status);
        }
        if (status < 0) {
            Integer newCount = cart.getCount() + status;
            if (newCount < 0) {
                newCount = 0;
            }
            cart.setCount(newCount);

        }
        cartRepository.save(cart);
    }
}
