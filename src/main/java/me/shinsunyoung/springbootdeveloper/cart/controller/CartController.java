package me.shinsunyoung.springbootdeveloper.cart.controller;

import me.shinsunyoung.springbootdeveloper.cart.dto.CartDto;
import me.shinsunyoung.springbootdeveloper.cart.service.CartService;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.service.CustomUserDetails;
import me.shinsunyoung.springbootdeveloper.service.UserDetailService;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final UserRepository userRepository;


    public CartController(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<Void> addCart(
            @PathVariable("productId") Long id,
            @AuthenticationPrincipal
            CustomUserDetails userDetails,
            @RequestBody CartDto dto
    ) {
        Long userId = userDetails.getUserId();
        cartService.addCart(id, userId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        cartService.deleteByCartId(id, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<CartDto>> findByUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        List<CartDto> cartDtos = cartService.findByUserIdWithProduct(userId);
        return ResponseEntity.ok().body(cartDtos);
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<Void> updateById(@PathVariable("productId") Long productId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestParam("status") Integer status) {
        Long userId = userDetails.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        cartService.updateById(productId, user, status);
        return ResponseEntity.ok().build();
    }
    }
