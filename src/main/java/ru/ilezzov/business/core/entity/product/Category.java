package ru.ilezzov.business.core.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;

@Table(name = "categories")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity {
    @Column(name = "name", length = 63, nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
