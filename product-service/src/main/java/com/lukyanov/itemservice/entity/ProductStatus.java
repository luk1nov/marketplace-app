package com.lukyanov.itemservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product_status")
public class ProductStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_status_gen")
    @SequenceGenerator(name = "product_status_gen", sequenceName = "product_status_seq", allocationSize = 1)
    private Long id;

    private String status;

    @OneToMany(mappedBy = "status")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Product> products = new HashSet<>();

    public ProductStatus(Long id, String status){
        this.id = id;
        this.status = status;
    }
}
