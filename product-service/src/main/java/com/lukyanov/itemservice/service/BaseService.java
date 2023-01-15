package com.lukyanov.itemservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, ID>{
    T findById(ID id);
    Page<T> findAll(Pageable pageable);
    void remove(ID id);
}
