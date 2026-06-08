package ru.ilezzov.business.core.entity.purchses;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;
import ru.ilezzov.business.core.entity.product.Product;
import ru.ilezzov.business.core.entity.suppliers.Supplier;

import java.math.BigDecimal;

@Table(name = "purchases")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "buy_price", nullable = false)
    private BigDecimal buyPrice = BigDecimal.ONE;
}