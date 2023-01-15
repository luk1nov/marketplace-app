package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.dto.ConditionDto;
import com.lukyanov.itemservice.mapper.ConditionMapper;
import com.lukyanov.itemservice.repository.ConditionRepository;
import com.lukyanov.itemservice.service.ConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService {
    private final ConditionRepository conditionRepository;
    private final ConditionMapper conditionMapper;

    @Override
    public ConditionDto findById(Long id) {
        return conditionRepository.findById(id)
                .map(conditionMapper::conditionToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<ConditionDto> findAll(Pageable pageable) {
        return conditionRepository.findAll(pageable)
                .map(conditionMapper::conditionToDto);
    }

    @Override
    public ConditionDto create(ConditionDto conditionDto) {
        return conditionMapper.conditionToDto(conditionRepository.save(conditionMapper.dtoToCondition(conditionDto)));
    }

    @Override
    public ConditionDto update(Long id, ConditionDto conditionDto) {
        findById(id);
        conditionDto.setId(id);
        return conditionMapper.conditionToDto(conditionRepository.save(conditionMapper.dtoToCondition(conditionDto)));
    }

    @Override
    public ConditionDto findByName(String name) {
        return conditionRepository.findFirstByName(name)
                .map(conditionMapper::conditionToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void remove(Long id) {
        conditionRepository.deleteById(id);
    }
}
