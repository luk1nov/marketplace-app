package com.lukyanov.itemservice.repository;

import com.lukyanov.itemservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
