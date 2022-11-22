package com.lukyanov.itemservice.mapper;

import com.lukyanov.itemservice.dto.RequestItemDto;
import com.lukyanov.itemservice.dto.ResponseItemDto;
import com.lukyanov.itemservice.entity.Item;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
uses = {ConditionMapper.class, CategoryMapper.class},
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemMapper {

    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "conditionDto", source = "condition")
    ResponseItemDto itemToDto(Item item);

    @Mapping(target = "category.name", source = "category")
    @Mapping(target = "condition.name", source = "condition")
    Item dtoToItem(RequestItemDto requestItemDto);
}
