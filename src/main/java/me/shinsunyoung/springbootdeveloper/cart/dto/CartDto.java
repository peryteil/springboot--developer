package me.shinsunyoung.springbootdeveloper.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.cart.entity.Cart;
import me.shinsunyoung.springbootdeveloper.product.dto.ProductDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Integer count;
    private Integer price;
    private ProductDto productDto;

    public CartDto(Cart cart) {
        this.id = cart.getId();
        this.price = cart.getPrice();
        this.count = cart.getCount();
        if (cart.getProduct() != null) {
            this.productDto = new ProductDto(cart.getProduct());
        }
    }

    public static CartDto fromEntity(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setPrice(cart.getPrice());
        dto.setCount(cart.getCount());
        if (cart.getProduct() != null) {
            dto.setProductDto(new ProductDto(cart.getProduct()));

        }
        return dto;
    }

}
