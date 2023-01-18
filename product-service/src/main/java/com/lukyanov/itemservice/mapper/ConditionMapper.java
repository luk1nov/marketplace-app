package com.lukyanov.itemservice.mapper;

import com.lukyanov.itemservice.dto.ConditionDto;
import com.lukyanov.itemservice.entity.Condition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConditionMapper {
    ConditionDto conditionToDto(Condition condition);
    @Mapping(target = "products", ignore = true)
    Condition dtoToCondition(ConditionDto conditionDto);
}
