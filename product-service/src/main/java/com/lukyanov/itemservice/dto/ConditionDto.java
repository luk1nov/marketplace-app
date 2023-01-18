package com.lukyanov.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionDto implements Serializable {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 55)
    private String name;

    public ConditionDto(String name) {
        this.name = name;
    }
}
