package me.shinsunyoung.springbootdeveloper.order.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.cart.entity.Cart;
import me.shinsunyoung.springbootdeveloper.cart.repository.CartRepository;
import me.shinsunyoung.springbootdeveloper.order.repository.OrderRepository;
import me.shinsunyoung.springbootdeveloper.order.dto.OrderDto;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import me.shinsunyoung.springbootdeveloper.order.entity.ProductManagement;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.product.repository.ProductRepository;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository; // ← UserRepository로 수정

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private static final String USER_NOT_FOUND = "회원 정보를 찾을 수 없습니다.";

    public Orders createOrder(List<Long> cartIds) {
        List<Cart> carts = cartRepository.findByIdIn(cartIds);

        Long userId = carts.get(0).getUser().getId();
        verifyUserIdMatch(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));

        List<ProductManagement> productMgts = new ArrayList<>();
        for (Cart cart : carts) {
            productMgts.add(cart.getProductManagement());
        }

        boolean sameUser = carts.stream()
                .allMatch(cart -> cart.getUser().getId().equals(userId));
        if (!sameUser || user == null) {
            return null;
        }

        return new Orders(
                user,
                productMgts,
                user.getNickname(), // username → nickname으로 가정
                getProductNames(carts),
                calculateTotalPrice(carts),
                getUserPhoneNumber(carts)
        );
    }

    private String getProductNames(List<Cart> carts) {
        StringBuilder productNamesBuilder = new StringBuilder();
        for (Cart cart : carts) {
            Long productId = cart.getProductManagement().getProduct().getId(); // ← 수정됨
            Product product = productRepository.findById(productId).orElse(null);

            if (product != null) {
                if (!productNamesBuilder.isEmpty()) {
                    productNamesBuilder.append(", ");
                }
                productNamesBuilder.append(product.getName());
            }
        }
        return productNamesBuilder.toString();
    }

    private String getUserPhoneNumber(List<Cart> carts) {
        Long userId = carts.get(0).getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
        return (user != null && user.getPhone() != null) ? user.getPhone() : null;
    }

    private BigDecimal calculateTotalPrice(List<Cart> carts) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Cart cart : carts) {
            BigDecimal cartPrice = BigDecimal.valueOf(cart.getPrice());
            totalPrice = totalPrice.add(cartPrice);
        }
        return totalPrice;
    }

    public Orders orderConfirm(Orders temporaryOrder, OrderDto orders) {
        String merchantUid = generateMerchantUid();
        temporaryOrder.orderConfirm(merchantUid, orders);
        return orderRepository.save(temporaryOrder);
    }

    private String generateMerchantUid() {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        String formattedDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return formattedDay + '-' + uniqueString;
    }

    private void verifyUserIdMatch(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("사용자 정보가 유효하지 않습니다.");
        }
    }
}
