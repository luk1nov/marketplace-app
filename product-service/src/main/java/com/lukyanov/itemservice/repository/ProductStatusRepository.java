package com.lukyanov.itemservice.repository;

import com.lukyanov.itemservice.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {

    Optional<ProductStatus> findByStatus(String status);

}
