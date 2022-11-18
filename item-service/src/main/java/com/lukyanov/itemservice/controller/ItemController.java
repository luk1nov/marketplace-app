package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.ItemDto;
import com.lukyanov.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> findAll(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ItemDto findById(@PathVariable UUID id){
        return itemService.findById(id);
    }

    @PostMapping
    public ItemDto create(@RequestBody ItemDto itemDto){
        return itemService.create(itemDto);
    }

    @PutMapping("/{id}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable UUID id){
        return itemService.update(id, itemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        itemService.remove(id);
    }
}
