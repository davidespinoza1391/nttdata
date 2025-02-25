package com.nttdata.nttdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "genero", length = 20)
    private String genero;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "identificacion", length = 50)
    private String identificacion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono", length = 50)
    private String telefono;

}