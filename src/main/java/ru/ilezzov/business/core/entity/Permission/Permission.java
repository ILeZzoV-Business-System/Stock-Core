package ru.ilezzov.business.core.entity.Permission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

@Table(name = "permissions")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends AbstractEntity {
    @Column(name = "name", length = 63, unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();
}
