package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FincaDTO {
    private int finca_id;
    private String nombre_finca;
    private String propietario;
    private String municipio;
    private String departamento;
    private double latitud;
    private double longitud;
    private String estado;
}
