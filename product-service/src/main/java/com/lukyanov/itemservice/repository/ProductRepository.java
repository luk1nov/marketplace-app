package com.lukyanov.itemservice.repository;

import com.lukyanov.itemservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
