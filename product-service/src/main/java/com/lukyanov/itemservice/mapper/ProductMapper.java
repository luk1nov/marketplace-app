package com.lukyanov.itemservice.mapper;

import com.lukyanov.itemservice.dto.RequestProductDto;
import com.lukyanov.itemservice.dto.ResponseProductDto;
import com.lukyanov.itemservice.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
uses = {ConditionMapper.class, CategoryMapper.class},
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    @Mapping(target = "responseCategoryDto", source = "category")
    @Mapping(target = "condition", source = "condition.name")
    @Mapping(target = "status", source = "status.status")
    ResponseProductDto productToDto(Product product);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "condition.name", source = "condition")
    Product dtoToProduct(RequestProductDto requestProductDto);
}
