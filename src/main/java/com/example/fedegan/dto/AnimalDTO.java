package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private int animal_id;
    private int finca_id;
    private String tipo_animal;
    private String raza;
    private String sexo;
    private int edad_meses;
}
