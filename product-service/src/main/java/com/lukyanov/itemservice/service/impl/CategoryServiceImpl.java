package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.dto.RequestCategoryDto;
import com.lukyanov.itemservice.dto.ResponseCategoryDto;
import com.lukyanov.itemservice.exception.EntityModifyingException;
import com.lukyanov.itemservice.mapper.CategoryMapper;
import com.lukyanov.itemservice.repository.CategoryRepository;
import com.lukyanov.itemservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseCategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<ResponseCategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::categoryToDto);
    }

    @Override
    public ResponseCategoryDto create(RequestCategoryDto requestCategoryDto) {
        return categoryMapper.categoryToDto(categoryRepository.save(categoryMapper.requestCategoryDtoToCategory(requestCategoryDto)));
    }

    @Override
    public ResponseCategoryDto update(Long id, RequestCategoryDto requestCategoryDto) {
        ResponseCategoryDto existCategory = findById(id);
        Set<Long> allChildCategoryIds = new HashSet<>();
        fillAllSubcategories(allChildCategoryIds, existCategory);
        if(allChildCategoryIds.contains(requestCategoryDto.getParentCategoryId())){
            throw new EntityModifyingException("Can not set child category as parent category");
        }
        requestCategoryDto.setId(id);
        return categoryMapper.categoryToDto(categoryRepository.save(categoryMapper.requestCategoryDtoToCategory(requestCategoryDto)));
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    void fillAllSubcategories(Set<Long> categorySet, ResponseCategoryDto responseCategoryDto){
        for (ResponseCategoryDto category: responseCategoryDto.getSubcategories()) {
            categorySet.add(category.getId());
            fillAllSubcategories(categorySet, category);
        }
    }
}
