package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.RequestItemDto;
import com.lukyanov.itemservice.dto.ResponseItemDto;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public interface ItemService extends BaseService<ResponseItemDto, UUID>{
    ResponseItemDto create(RequestItemDto t);
    ResponseItemDto update(UUID id, RequestItemDto t);
}
