package com.nttdata.nttdata.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class TransaccionRequest {
    private Integer idCuenta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

}