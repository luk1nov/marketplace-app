package com.lukyanov.itemservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_gen")
    @SequenceGenerator(name = "condition_gen", sequenceName = "condition_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "condition")
    private Set<Item> items = new HashSet<>();
}
