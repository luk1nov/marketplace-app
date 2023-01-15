package com.lukyanov.itemservice.mapper;


import com.lukyanov.itemservice.dto.RequestCategoryDto;
import com.lukyanov.itemservice.dto.ResponseCategoryDto;
import com.lukyanov.itemservice.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    ResponseCategoryDto categoryToDto(Category category);
    @Mapping(target = "parentCategory.id", source = "parentCategoryId")
    Category requestCategoryDtoToCategory(RequestCategoryDto requestCategoryDto);

    Category responseCategoryDtoToCategory(ResponseCategoryDto responseCategoryDto);
}
