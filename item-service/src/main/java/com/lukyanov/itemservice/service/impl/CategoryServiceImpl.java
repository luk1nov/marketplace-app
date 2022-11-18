package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.dto.CategoryDto;
import com.lukyanov.itemservice.mapper.CategoryMapper;
import com.lukyanov.itemservice.repository.CategoryRepository;
import com.lukyanov.itemservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToDto)
                .toList();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return categoryMapper.categoryToDto(categoryRepository.save(categoryMapper.DtoToCategory(categoryDto)));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        findById(id);
        categoryDto.setId(id);
        return categoryMapper.categoryToDto(categoryRepository.save(categoryMapper.DtoToCategory(categoryDto)));
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }
}
