package com.lukyanov.itemservice.repository;

import com.lukyanov.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
