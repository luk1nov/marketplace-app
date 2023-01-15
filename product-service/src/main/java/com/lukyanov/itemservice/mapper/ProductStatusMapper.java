package com.lukyanov.itemservice.mapper;

import com.lukyanov.itemservice.dto.ProductStatusDto;
import com.lukyanov.itemservice.entity.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductStatusMapper {

    ProductStatus dtoToProductStatus(ProductStatusDto dto);

    ProductStatusDto productStatusToDto(ProductStatus productStatus);
}
