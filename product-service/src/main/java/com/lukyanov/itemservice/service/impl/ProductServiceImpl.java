package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.client.UserApiClient;
import com.lukyanov.itemservice.dto.*;
import com.lukyanov.itemservice.entity.Product;
import com.lukyanov.itemservice.mapper.CategoryMapper;
import com.lukyanov.itemservice.mapper.ConditionMapper;
import com.lukyanov.itemservice.mapper.ProductMapper;
import com.lukyanov.itemservice.mapper.ProductStatusMapper;
import com.lukyanov.itemservice.repository.ProductRepository;
import com.lukyanov.itemservice.service.CategoryService;
import com.lukyanov.itemservice.service.ConditionService;
import com.lukyanov.itemservice.service.ProductService;
import com.lukyanov.itemservice.service.ProductStatusService;
import com.lukyanov.itemservice.spec.ProductSearchCriteria;
import com.lukyanov.itemservice.spec.ProductSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ConditionService conditionService;
    private final CategoryService categoryService;
    private final ProductStatusService productStatusService;
    private final ProductStatusMapper productStatusMapper;
    private final ProductMapper productMapper;
    private final ConditionMapper conditionMapper;
    private final CategoryMapper categoryMapper;
    private final UserApiClient userApiClient;

    @Override
    public ResponseProductDto findById(Long id) {
        return productRepository.findById(id).map(productMapper::productToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<ResponseProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::productToDto);
    }

    @Override
    public ResponseProductDto create(RequestProductDto requestProductDto, String authorizationHeader) {
        UserDto currentUser = userApiClient.getUserByAuth(authorizationHeader);
        Product product = buildItemFromRequestItem(requestProductDto);
        product.setSellerId(currentUser.getId());
        return productMapper.productToDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ResponseProductDto update(Long id, RequestProductDto requestProductDto) {
        findById(id);
        Product updatedProduct = buildItemFromRequestItem(requestProductDto);
        updatedProduct.setId(id);
        return productMapper.productToDto(productRepository.saveAndFlush(updatedProduct));
    }

    @Override
    public Page<ResponseProductDto> findAllWithParams(Pageable pageable, ProductSearchCriteria criteria) {
        Optional<Specification<Product>> productsByCriteria = ProductSpecs.getProductsByCriteria(criteria);
        return productsByCriteria.isEmpty() ?
                findAll(pageable) :
                productRepository.findAll(productsByCriteria.get(), pageable)
                        .map(productMapper::productToDto);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    private Product buildItemFromRequestItem(RequestProductDto requestProductDto){
        ResponseCategoryDto responseCategoryDto = Objects.nonNull(requestProductDto.getCategoryId()) ? categoryService.findById(requestProductDto.getCategoryId()) : null;
        ConditionDto conditionDto = conditionService.findByName(requestProductDto.getCondition());
        Product product = productMapper.dtoToProduct(requestProductDto);
        product.setCategory(categoryMapper.responseCategoryDtoToCategory(responseCategoryDto));
        product.setCondition(conditionMapper.dtoToCondition(conditionDto));
        product.setStatus(productStatusMapper.dtoToProductStatus(productStatusService.getDefaultStatus()));
        return product;
    }
}
