package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.CategoryDto;

public interface CategoryService extends BaseService<CategoryDto, Long> {
    CategoryDto create(CategoryDto t);
    CategoryDto update(Long id, CategoryDto t);
    CategoryDto findByName(String name);
}
