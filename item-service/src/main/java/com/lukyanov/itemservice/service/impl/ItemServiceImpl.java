package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.dto.CategoryDto;
import com.lukyanov.itemservice.dto.ConditionDto;
import com.lukyanov.itemservice.dto.RequestItemDto;
import com.lukyanov.itemservice.dto.ResponseItemDto;
import com.lukyanov.itemservice.entity.Item;
import com.lukyanov.itemservice.mapper.CategoryMapper;
import com.lukyanov.itemservice.mapper.ConditionMapper;
import com.lukyanov.itemservice.mapper.ItemMapper;
import com.lukyanov.itemservice.repository.ItemRepository;
import com.lukyanov.itemservice.service.CategoryService;
import com.lukyanov.itemservice.service.ConditionService;
import com.lukyanov.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ConditionService conditionService;
    private final CategoryService categoryService;
    private final ItemMapper itemMapper;
    private final ConditionMapper conditionMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseItemDto findById(UUID uuid) {
        return itemRepository.findById(uuid).map(itemMapper::itemToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ResponseItemDto> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::itemToDto)
                .toList();
    }

    @Override
    public ResponseItemDto create(RequestItemDto requestItemDto) {
        return itemMapper.itemToDto(itemRepository.save(buildItemFromRequestItem(requestItemDto)));
    }

    @Override
    public ResponseItemDto update(UUID uuid, RequestItemDto requestItemDto) {
//        findById(uuid);
//        requestItemDto.setId(uuid);
//        return itemMapper.itemToDto(itemRepository.save(itemMapper.dtoToItem(requestItemDto)));
        //todo
        return null;
    }

    @Override
    public void remove(UUID uuid) {
        itemRepository.deleteById(uuid);
    }

    private Item buildItemFromRequestItem(RequestItemDto requestItemDto){
        CategoryDto categoryDto = categoryService.findByName(requestItemDto.getCategory());
        ConditionDto conditionDto = conditionService.findByName(requestItemDto.getCondition());
        Item item = itemMapper.dtoToItem(requestItemDto);
        item.setCategory(categoryMapper.dtoToCategory(categoryDto));
        item.setCondition(conditionMapper.dtoToCondition(conditionDto));
        return item;
    }
}
