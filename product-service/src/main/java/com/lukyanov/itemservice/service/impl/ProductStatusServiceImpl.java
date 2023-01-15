package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.config.ProductStatusPropertyConfig;
import com.lukyanov.itemservice.dto.ProductStatusDto;
import com.lukyanov.itemservice.mapper.ProductStatusMapper;
import com.lukyanov.itemservice.repository.ProductStatusRepository;
import com.lukyanov.itemservice.service.ProductStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductStatusServiceImpl implements ProductStatusService {
    private final ProductStatusRepository repository;
    private final ProductStatusMapper mapper;
    private final ProductStatusPropertyConfig config;

    public ProductStatusDto getDefaultStatus(){
        return repository.findByStatus(config.getDefaultStatus())
                .map(mapper::productStatusToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductStatusDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::productStatusToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<ProductStatusDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::productStatusToDto);
    }

    @Override
    public void remove(Long id) {
        ProductStatusDto productStatusDto = findById(id);
        repository.delete(mapper.dtoToProductStatus(productStatusDto));
    }

    @Override
    public ProductStatusDto findByStatus(String status) {
        return repository.findByStatus(status)
                .map(mapper::productStatusToDto)
                .orElseThrow(EntityNotFoundException::new);
    }
}
