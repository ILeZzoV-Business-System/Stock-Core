package ru.ilezzov.business.core.entity.sale;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;
import ru.ilezzov.business.core.entity.product.Product;
import ru.ilezzov.business.core.entity.user.User;

import java.math.BigDecimal;

@Table(name = "sales")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "sell_price", nullable = false)
    private BigDecimal sellPrice = BigDecimal.ZERO;
}
