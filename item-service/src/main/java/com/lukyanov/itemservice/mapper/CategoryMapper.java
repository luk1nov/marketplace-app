package com.lukyanov.itemservice.mapper;


import com.lukyanov.itemservice.dto.CategoryDto;
import com.lukyanov.itemservice.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    CategoryDto categoryToDto(Category category);
    Category DtoToCategory(CategoryDto categoryDto);
}
