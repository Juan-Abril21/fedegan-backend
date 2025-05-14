package com.example.fedegan.orm;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "campania_vacunacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaniaVacunacionORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campania_id")
    private int campania_id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_inicio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio;

    @Column(name = "fecha_fin")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fin;

    @Column(name = "estado")
    private String estado;
}
