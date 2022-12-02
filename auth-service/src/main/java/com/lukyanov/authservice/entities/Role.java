package com.lukyanov.authservice.entities;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    @SequenceGenerator(name = "role_gen", sequenceName = "role_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AuthUser> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="role_has_permission",
            joinColumns = @JoinColumn(name="role_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="permission_id", referencedColumnName="id")
    )
    private Set<Permission> permissions = new HashSet<>();
}
