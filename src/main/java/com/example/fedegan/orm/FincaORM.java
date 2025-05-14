package com.example.fedegan.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "finca")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FincaORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "finca_id")
    private Long finca_id;

    @Column(name = "nombre_finca")
    private String nombre_finca;

    @Column(name = "propietario")
    private String propietario;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;

    @Column(name = "estado")
    private String estado;
}
