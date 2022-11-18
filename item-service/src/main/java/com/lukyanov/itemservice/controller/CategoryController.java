package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.CategoryDto;
import com.lukyanov.itemservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto){
        return categoryService.create(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@RequestBody CategoryDto categoryDto, @PathVariable Long id){
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        categoryService.remove(id);
    }
}
