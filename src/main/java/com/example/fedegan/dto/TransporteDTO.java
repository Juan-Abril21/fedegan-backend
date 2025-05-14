package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporteDTO {
    private int transporte_id;
    private int animal_id;
    private int origen_finca_id;
    private int destino_finca_id;
    private String fecha_transporte;
    private String motivo;
}
