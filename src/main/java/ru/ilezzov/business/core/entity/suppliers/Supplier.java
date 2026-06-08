package ru.ilezzov.business.core.entity.suppliers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;

@Table(name = "suppliers")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends AbstractEntity {
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contact_id", nullable = false, unique = true)
    private Contact contact;
}
