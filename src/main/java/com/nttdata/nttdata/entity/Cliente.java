package com.nttdata.nttdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cliente_id", nullable = false, length = 50)
    private String clienteId;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "estado")
    private Boolean estado;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "persona_id")
//    private Persona persona;

}