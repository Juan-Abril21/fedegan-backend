package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaniaVacunacionDTO {
    private int campania_id;
    private String nombre;
    private String fecha_inicio;
    private String fecha_fin;
    private String estado;
}
