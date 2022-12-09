package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.dto.CategoryDto;
import com.lukyanov.itemservice.dto.ConditionDto;
import com.lukyanov.itemservice.dto.RequestProductDto;
import com.lukyanov.itemservice.dto.ResponseProductDto;
import com.lukyanov.itemservice.entity.Product;
import com.lukyanov.itemservice.mapper.CategoryMapper;
import com.lukyanov.itemservice.mapper.ConditionMapper;
import com.lukyanov.itemservice.mapper.ProductMapper;
import com.lukyanov.itemservice.repository.ProductRepository;
import com.lukyanov.itemservice.service.CategoryService;
import com.lukyanov.itemservice.service.ConditionService;
import com.lukyanov.itemservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ConditionService conditionService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;
    private final ConditionMapper conditionMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseProductDto findById(Long id) {
        return productRepository.findById(id).map(productMapper::itemToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ResponseProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::itemToDto)
                .toList();
    }

    @Override
    public ResponseProductDto create(RequestProductDto requestProductDto) {
        return productMapper.itemToDto(productRepository.save(buildItemFromRequestItem(requestProductDto)));
    }

    @Override
    public ResponseProductDto update(Long id, RequestProductDto requestProductDto) {
//        findById(uuid);
//        requestItemDto.setId(uuid);
//        return itemMapper.itemToDto(itemRepository.save(itemMapper.dtoToItem(requestItemDto)));
        //todo
        return null;
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    private Product buildItemFromRequestItem(RequestProductDto requestProductDto){
        CategoryDto categoryDto = categoryService.findByName(requestProductDto.getCategory());
        ConditionDto conditionDto = conditionService.findByName(requestProductDto.getCondition());
        Product product = productMapper.dtoToItem(requestProductDto);
        product.setCategory(categoryMapper.dtoToCategory(categoryDto));
        product.setCondition(conditionMapper.dtoToCondition(conditionDto));
        return product;
    }
}
