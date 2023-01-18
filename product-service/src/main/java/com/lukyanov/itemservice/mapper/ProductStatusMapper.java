package com.lukyanov.itemservice.mapper;

import com.lukyanov.itemservice.dto.ProductStatusDto;
import com.lukyanov.itemservice.entity.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductStatusMapper {

    @Mapping(target = "products", ignore = true)
    ProductStatus dtoToProductStatus(ProductStatusDto dto);

    ProductStatusDto productStatusToDto(ProductStatus productStatus);
}
