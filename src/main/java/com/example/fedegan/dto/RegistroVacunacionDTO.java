package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVacunacionDTO {
    private int registro_id;
    private int vacunador_id;
    private int animal_id;
    private int campania_id;
    private LocalDateTime fecha_aplicacion;
    private String resultado;
    private String observaciones;
    private boolean sincronizado;
}
