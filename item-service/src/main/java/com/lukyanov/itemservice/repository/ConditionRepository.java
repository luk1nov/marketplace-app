package com.lukyanov.itemservice.repository;

import com.lukyanov.itemservice.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
