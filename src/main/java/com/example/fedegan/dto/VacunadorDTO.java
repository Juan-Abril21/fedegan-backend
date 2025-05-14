package com.example.fedegan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacunadorDTO {
    private int vacunador_id;
    private String nomrbe_completo;
    private int documento;
    private int telefono;
    private String email;
    private String zona_asignada;
    private String fecha_registro;
}
