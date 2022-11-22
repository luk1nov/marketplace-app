package com.lukyanov.itemservice.repository;

import com.lukyanov.itemservice.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
    Optional<Condition> findFirstByName(String name);
}
