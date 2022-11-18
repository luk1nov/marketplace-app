package com.lukyanov.itemservice.service;

import java.util.List;

public interface BaseService<T, ID>{
    T findById(ID id);
    List<T> findAll();
    T create(T t);
    T update(ID id, T t);
    void remove(ID id);
}
