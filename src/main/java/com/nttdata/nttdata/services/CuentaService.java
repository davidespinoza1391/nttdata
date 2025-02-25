package com.nttdata.nttdata.services;

import com.nttdata.nttdata.entity.Cuenta;

import java.util.List;

public interface CuentaService {
    Cuenta createCuenta(Cuenta cuenta);
    List<Cuenta> getAllCuentas();
    Cuenta updateCuenta(Integer id, Cuenta cuentaDetails);
    void deleteCuenta(Integer id);
}
