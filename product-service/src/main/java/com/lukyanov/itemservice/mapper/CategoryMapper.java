package com.lukyanov.itemservice.mapper;


import com.lukyanov.itemservice.dto.RequestCategoryDto;
import com.lukyanov.itemservice.dto.ResponseCategoryDto;
import com.lukyanov.itemservice.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    ResponseCategoryDto categoryToResponseCategoryDto(Category category);

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category responseCategoryDtoToCategory(ResponseCategoryDto responseCategoryDto);
    default Category requestCategoryDtoToCategory(RequestCategoryDto requestCategoryDto){
        if ( requestCategoryDto == null ) {
            return null;
        }

        Category category = new Category();
        Category parentCategory = null;
        Long parentCategoryId = requestCategoryDto.getParentCategoryId();
        if(Objects.nonNull(parentCategoryId)){
            parentCategory = new Category();
            parentCategory.setId(parentCategoryId);
        }
        category.setParentCategory(parentCategory);
        category.setId( requestCategoryDto.getId() );
        category.setName( requestCategoryDto.getName() );
        category.setDescription( requestCategoryDto.getDescription() );

        return category;
    }
}
