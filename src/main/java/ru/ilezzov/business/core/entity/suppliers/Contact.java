package ru.ilezzov.business.core.entity.suppliers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;

@Table(name = "contacts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends AbstractEntity {
    @Column(name = "type", length = 63, nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private String value;
}
