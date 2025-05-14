package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BroteDTO {
    private int brote_id;
    private String municipio;
    private String fecha_reporte;
    private String tipo_enfermedad;
    private int numero_casos;
    private String estado_brote;
}
