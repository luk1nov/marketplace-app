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

    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "conditionDto", source = "condition")
    ResponseProductDto itemToDto(Product product);

    @Mapping(target = "category.name", source = "category")
    @Mapping(target = "condition.name", source = "condition")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "created", ignore = true)
//    @Mapping(target = "updated", ignore = true)
    Product dtoToItem(RequestProductDto requestProductDto);
}
