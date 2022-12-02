package com.lukyanov.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionDto implements Serializable {
    private Long id;
    private String name;
    public ConditionDto(String name) {
        this.name = name;
    }
}
