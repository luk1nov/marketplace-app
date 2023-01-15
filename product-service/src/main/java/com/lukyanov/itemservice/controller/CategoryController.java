package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.RequestCategoryDto;
import com.lukyanov.itemservice.dto.ResponseCategoryDto;
import com.lukyanov.itemservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public Page<ResponseCategoryDto> findAll(Pageable pageable){
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @PostMapping
    public ResponseCategoryDto create(@RequestBody RequestCategoryDto requestCategoryDto){
        return categoryService.create(requestCategoryDto);
    }

    @PutMapping("/{id}")
    public ResponseCategoryDto update(@RequestBody RequestCategoryDto requestCategoryDto, @PathVariable Long id){
        return categoryService.update(id, requestCategoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        categoryService.remove(id);
    }
}
