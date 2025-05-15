package com.example.fedegan.orm;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "transporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporteORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transporte_id")
    private Long transporte_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", referencedColumnName = "animal_id", nullable = false)
    private AnimalORM animal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "finca_origen_id", referencedColumnName = "finca_id", nullable = false)
    private FincaORM fincaOrigen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "finca_destino_id", referencedColumnName = "finca_id", nullable = false)
    private FincaORM fincaDestino;

    @Column(name = "fecha_transporte")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaTransporte;

    @Column(name = "motivo")
    private String motivo;
}
