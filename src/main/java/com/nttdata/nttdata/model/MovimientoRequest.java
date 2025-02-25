package com.nttdata.nttdata.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class MovimientoRequest {

    private LocalDate fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    private String numeroCuenta;  // Este campo reemplaza la referencia a Cuenta

}