package com.nttdata.nttdata.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistroRequest {

    private String idCliente;
    private String contrasena;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

}