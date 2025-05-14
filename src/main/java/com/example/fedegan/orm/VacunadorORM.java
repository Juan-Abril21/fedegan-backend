package com.example.fedegan.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vacunador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacunadorORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacunador_id")
    private Long vacunador_id;

    @Column(name = "nombre_completo")
    private String nombre_completo;

    @Column(name = "documento")
    private Long documento;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "zona_asignada")
    private String zona_asignada;

    @Column(name = "fecha_registro")
    private LocalDateTime fecha_registro;
}
