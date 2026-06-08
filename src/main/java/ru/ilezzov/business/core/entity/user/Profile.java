package ru.ilezzov.business.core.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ilezzov.business.core.entity.AbstractEntity;

@Table(name = "profiles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends AbstractEntity {
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "username", length = 63, nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", length = 63, nullable = false)
    private String firstName = "No Name";

    @Column(name = "second_name", length = 63)
    private String secondName;
}
