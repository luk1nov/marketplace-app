package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.ConditionDto;
import com.lukyanov.itemservice.service.ConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conditions")
@RequiredArgsConstructor
public class ConditionController {
    private final ConditionService conditionService;

    @GetMapping
    public List<ConditionDto> findAll(){
        return conditionService.findAll();
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
