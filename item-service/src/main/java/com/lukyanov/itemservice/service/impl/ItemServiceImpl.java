package com.lukyanov.itemservice.service.impl;

import com.lukyanov.itemservice.dto.ItemDto;
import com.lukyanov.itemservice.mapper.ItemMapper;
import com.lukyanov.itemservice.repository.ItemRepository;
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
    private final ItemMapper itemMapper;

    @Override
    public ItemDto findById(UUID uuid) {
        return itemRepository.findById(uuid).map(itemMapper::itemToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::itemToDto)
                .toList();
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        return itemMapper.itemToDto(itemRepository.save(itemMapper.dtoToItem(itemDto)));
    }

    @Override
    public ItemDto update(UUID uuid, ItemDto itemDto) {
        findById(uuid);
        itemDto.setId(uuid);
        return itemMapper.itemToDto(itemRepository.save(itemMapper.dtoToItem(itemDto)));
    }

    @Override
    public void remove(UUID uuid) {
        itemRepository.deleteById(uuid);
    }
}
