package me.shinsunyoung.springbootdeveloper.order.entity;

import jakarta.persistence.*;
import lombok.*;
import me.shinsunyoung.springbootdeveloper.domain.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String productName;

    private BigDecimal totalPrice;

    private String status;

    private LocalDateTime orderedAt;
}
