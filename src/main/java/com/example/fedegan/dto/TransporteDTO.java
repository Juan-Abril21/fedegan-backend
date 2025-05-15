package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporteDTO {
    private Long transporte_id;
    private Long animal_id;
    private Long origen_finca_id;
    private Long destino_finca_id;
    private Date fecha_transporte;
    private String motivo;
}
