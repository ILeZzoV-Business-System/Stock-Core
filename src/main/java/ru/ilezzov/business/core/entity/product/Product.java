package ru.ilezzov.business.core.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;

import java.math.BigDecimal;

@Table(name = "products")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {
    @Column(name = "name", length = 63, nullable = false, unique = true)
    private String name;

    @Column(name = "status", length = 63)
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

    @Column(name = "price", nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketplace_id", nullable = false)
    private Marketplace marketplace;

    public enum Status {
        DRAFT,
        ACTIVE,
        OUT_OF_STOCK,
        HIDDEN,
        DISCOUNTED,
        ARCHIVED,
        ON_MARKETPLACE
    }
}
