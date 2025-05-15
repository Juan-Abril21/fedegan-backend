package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private Long animal_id;
    private Long finca_id;
    private String tipo_animal;
    private String raza;
    private String sexo;
    private int edad_meses;
}
