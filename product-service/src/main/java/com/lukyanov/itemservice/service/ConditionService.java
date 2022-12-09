package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.ConditionDto;

public interface ConditionService extends BaseService<ConditionDto, Long>{
    ConditionDto create(ConditionDto t);
    ConditionDto update(Long id, ConditionDto t);
    ConditionDto findByName(String name);
}
