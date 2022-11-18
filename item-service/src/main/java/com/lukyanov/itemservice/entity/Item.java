package com.lukyanov.itemservice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;

    @ManyToOne
    @JoinColumn(name = "condition_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Condition condition;

    @PrePersist
    public void setCreatedAndUpdated(){
        created = LocalDateTime.now();
        updated = created;
    }

    @PreUpdate
    public void setUpdated(){
        updated = LocalDateTime.now();
    }
}
