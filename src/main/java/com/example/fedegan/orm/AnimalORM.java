package com.example.fedegan.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long animal_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "finca_id", referencedColumnName = "finca_id", nullable = false)
    private FincaORM finca;

    @Column(name = "tipo_animal")
    private String tipo_animal;

    @Column(name = "raza")
    private String raza;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "edad_meses")
    private int edad_meses;

}
