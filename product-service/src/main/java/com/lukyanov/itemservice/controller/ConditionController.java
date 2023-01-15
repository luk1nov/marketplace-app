package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.ConditionDto;
import com.lukyanov.itemservice.service.ConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conditions")
@RequiredArgsConstructor
public class ConditionController {
    private final ConditionService conditionService;

    @GetMapping
    public Page<ConditionDto> findAll(Pageable pageable){
        return conditionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ConditionDto findById(@PathVariable Long id){
        return conditionService.findById(id);
    }

    @PostMapping
    public ConditionDto create(@RequestBody ConditionDto conditionDto){
        return conditionService.create(conditionDto);
    }

    @PutMapping("/{id}")
    public ConditionDto update(@RequestBody ConditionDto conditionDto, @PathVariable Long id){
        return conditionService.update(id, conditionDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        conditionService.remove(id);
    }
}
