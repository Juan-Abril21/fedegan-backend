package com.example.fedegan.orm;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "registrovacunacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVacunacionORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_id")
    private Long registro_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vacunador_id", referencedColumnName = "vacunador_id", nullable = false)
    private VacunadorORM vacunador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", referencedColumnName = "animal_id", nullable = false)
    private AnimalORM animal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campania_id", referencedColumnName = "campania_id", nullable = false)
    private CampaniaVacunacionORM campania;

    @Column(name = "fecha_aplicacion")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_aplicacion;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "sincronizado")
    private boolean sincronizado;
}
