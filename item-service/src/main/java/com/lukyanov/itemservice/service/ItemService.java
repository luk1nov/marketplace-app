package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.RequestItemDto;
import com.lukyanov.itemservice.dto.ResponseItemDto;

public interface ItemService extends BaseService<ResponseItemDto, Long>{
    ResponseItemDto create(RequestItemDto t);
    ResponseItemDto update(Long id, RequestItemDto t);
}
