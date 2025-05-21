package me.shinsunyoung.springbootdeveloper.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.order.dto.CartItemDto;
import me.shinsunyoung.springbootdeveloper.order.dto.OrderRequestDto;
import me.shinsunyoung.springbootdeveloper.order.entity.OrderItem;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import me.shinsunyoung.springbootdeveloper.order.entity.ProductOrder;
import me.shinsunyoung.springbootdeveloper.order.repository.OrdersRepository;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createOrder(OrderRequestDto dto, User user) {
        ProductOrder order = new ProductOrder();
        order.setUser(user);
        order.setBuyerName(dto.getName());
        order.setBuyerEmail(dto.getEmail());
        order.setBuyerTel(dto.getTel());
        order.setBuyerAddr(dto.getAddr());
        order.setBuyerPostcode(dto.getPostcode());
        order.setMerchantUid(dto.getMerchantUid());
        order.setTotalAmount(dto.getAmount());

        for (CartItemDto itemDto : dto.getCartItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("상품 없음"));

            OrderItem item = new OrderItem();
            item.setProductOrder(order); // 여기 변경됨
            item.setProduct(product);
            item.setCount(itemDto.getCount());
            item.setPrice(BigDecimal.valueOf(product.getPrice()));

            order.getOrderItems().add(item);
        }

        ordersRepository.save(order);
    }
}
