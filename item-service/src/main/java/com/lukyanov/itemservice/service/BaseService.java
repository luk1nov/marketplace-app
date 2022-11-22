package com.lukyanov.itemservice.service;

import java.util.List;

public interface BaseService<T, ID>{
    T findById(ID id);
    List<T> findAll();
    void remove(ID id);
}
