package com.example.fedegan.orm;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "brote")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BroteORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brote_id")
    private Long brote_id;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "fecha_reporte")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_reporte;

    @Column(name = "tipo_enfermedad")
    private String tipo_enfermedad;

    @Column(name = "numero_casos")
    private int numero_casos;

    @Column(name = "estado_brote")
    private String estado_brote;

}
