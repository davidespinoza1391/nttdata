package com.nttdata.nttdata.controller;

import com.nttdata.nttdata.entity.Cuenta;
import com.nttdata.nttdata.services.imp.CuentaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaServiceImp cuentaServiceImp;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaServiceImp.getAllCuentas();
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaServiceImp.createCuenta(cuenta);
    }

    @PutMapping("/{id}")
    public Cuenta updateCuenta(@PathVariable Integer id, @RequestBody Cuenta cuentaDetails) {
        return cuentaServiceImp.updateCuenta(id, cuentaDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCuenta(@PathVariable Integer id) {
        cuentaServiceImp.deleteCuenta(id);
    }
}