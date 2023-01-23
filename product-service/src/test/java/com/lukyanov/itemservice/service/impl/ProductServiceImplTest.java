package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.client.UserApiClient;
import com.lukyanov.itemservice.dto.*;
import com.lukyanov.itemservice.entity.Category;
import com.lukyanov.itemservice.entity.Condition;
import com.lukyanov.itemservice.entity.Product;
import com.lukyanov.itemservice.entity.ProductStatus;
import com.lukyanov.itemservice.mapper.CategoryMapper;
import com.lukyanov.itemservice.mapper.ConditionMapper;
import com.lukyanov.itemservice.mapper.ProductMapper;
import com.lukyanov.itemservice.mapper.ProductStatusMapper;
import com.lukyanov.itemservice.repository.ProductRepository;
import com.lukyanov.itemservice.service.CategoryService;
import com.lukyanov.itemservice.service.ConditionService;
import com.lukyanov.itemservice.service.ProductStatusService;
import com.lukyanov.itemservice.spec.ProductSearchCriteria;
import com.lukyanov.itemservice.spec.ProductSpecs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private RequestProductDto requestProductDto;
    private ResponseProductDto responseProductDto;
    private ResponseCategoryDto responseCategoryDto;
    private ConditionDto conditionDto;
    private UserDto userDto;
    private Product product;
    private static final LocalDateTime currentTime = LocalDateTime.now();

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserApiClient userApiClient;
    @Mock
    private ConditionService conditionService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private ProductStatusService productStatusService;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private ConditionMapper conditionMapper;
    @Mock
    private CategoryMapper categoryMapper;
    @Mock
    private ProductStatusMapper productStatusMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Product", "desc", BigDecimal.TEN,
                new Category(), new Condition(), 1L, new ProductStatus(1L, "status"),
                currentTime, currentTime);
        responseCategoryDto = new ResponseCategoryDto("Category", "desc", new HashSet<>());
        responseProductDto = new ResponseProductDto(1L, "Product", "desc", BigDecimal.TEN,
                1L, "MODERATION", responseCategoryDto, "good",
               currentTime, currentTime);
        RequestCategoryDto requestCategoryDto = new RequestCategoryDto(1L, "category", "desc", null);
        requestProductDto = new RequestProductDto("product", "desc", BigDecimal.TEN, requestCategoryDto.getId(), "good");
        conditionDto = new ConditionDto(1L, "good");
        userDto = new UserDto();
        userDto.setId(1L);
    }

    @Test
    void findById() {
        given(productRepository.findById(anyLong())).willReturn(Optional.of(product));
        given(productMapper.productToDto(any(Product.class))).willReturn(responseProductDto);
        ResponseProductDto foundProduct = productService.findById(1L);
        assertThat(foundProduct).isEqualTo(responseProductDto);
    }

    @Test
    void findAll() {
        List<Product> products = List.of(product);
        Page<Product> pagedResponse = new PageImpl<>(products);
        given(productRepository.findAll(any(Pageable.class))).willReturn(pagedResponse);
        given(productMapper.productToDto(any(Product.class))).willReturn(responseProductDto);

        Page<ResponseProductDto> productPage = productService.findAll(Pageable.unpaged());
        assertThat(productPage.getContent())
                .hasSize(1)
                .contains(responseProductDto);
    }

    @Test
    void create() {
        given(productRepository.save(any(Product.class))).willReturn(product);
        given(categoryService.findById(anyLong())).willReturn(responseCategoryDto);
        given(conditionService.findByName(anyString())).willReturn(conditionDto);
        given(productStatusService.getDefaultStatus()).willReturn(new ProductStatusDto());
        given(userApiClient.getUserByAuth(anyString())).willReturn(userDto);
        mockMappers();

        ResponseProductDto createdProduct = productService.create(requestProductDto, "Bearer token");

        assertThat(createdProduct).isEqualTo(responseProductDto);

    }

    @Test
    void update() {
        given(productRepository.save(any(Product.class))).willReturn(product);
        given(productRepository.findById(anyLong())).willReturn(Optional.of(product));
        given(categoryService.findById(anyLong())).willReturn(responseCategoryDto);
        given(conditionService.findByName(anyString())).willReturn(conditionDto);
        given(productStatusService.getDefaultStatus()).willReturn(new ProductStatusDto());

        mockMappers();

        ResponseProductDto productDto = productService.update(1L, requestProductDto);

        assertThat(productDto).isEqualTo(responseProductDto);
    }

    @Test
    void findAllWithParams() {
        List<Product> products = List.of(product);
        Page<Product> pagedResponse = new PageImpl<>(products);
        given(productRepository.findAll(any(Pageable.class))).willReturn(pagedResponse);
        given(productMapper.productToDto(any(Product.class))).willReturn(responseProductDto);

        try (MockedStatic<ProductSpecs> utilities = Mockito.mockStatic(ProductSpecs.class)) {
            utilities.when(() -> ProductSpecs.getProductsByCriteria(any(ProductSearchCriteria.class)))
                    .thenReturn(Optional.empty());

            Page<ResponseProductDto> productPage = productService.findAllWithParams(Pageable.unpaged(), new ProductSearchCriteria());
            assertThat(productPage.getContent())
                    .hasSize(1)
                    .contains(responseProductDto);
        }

    }

    private void mockMappers(){
        given(productMapper.productToDto(any(Product.class))).willReturn(responseProductDto);
        given(productMapper.dtoToProduct(any(RequestProductDto.class))).willReturn(product);
        given(conditionMapper.dtoToCondition(any(ConditionDto.class))).willReturn(new Condition());
        given(categoryMapper.responseCategoryDtoToCategory(any(ResponseCategoryDto.class))).willReturn(new Category());
        given(productStatusMapper.dtoToProductStatus(any(ProductStatusDto.class))).willReturn(new ProductStatus());
    }
}