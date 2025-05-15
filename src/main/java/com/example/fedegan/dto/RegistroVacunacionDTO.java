package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVacunacionDTO {
    private Long registro_id;
    private Long vacunador_id;
    private Long animal_id;
    private int campania_id;
    private Date fecha_aplicacion;
    private String resultado;
    private String observaciones;
    private boolean sincronizado;
}
