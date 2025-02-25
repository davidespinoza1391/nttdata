package com.nttdata.nttdata.controller;


import com.nttdata.nttdata.model.MovimientoRequest;
import com.nttdata.nttdata.model.TransaccionRequest;
import com.nttdata.nttdata.entity.Movimiento;
import com.nttdata.nttdata.services.imp.MovimientoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoServiceImp movimientoServiceImp;


    @GetMapping("/{cuentaId}")
    public List<Movimiento> getMovimientosByCuenta(@PathVariable Integer cuentaId) {
        return movimientoServiceImp.getMovimientosByCuenta(cuentaId);
    }

    @PostMapping
    public Movimiento createMovimiento(@RequestBody MovimientoRequest movimientoRequest) {
        return movimientoServiceImp.createMovimiento(movimientoRequest);
    }

    @PutMapping("/{id}")
    public Movimiento updateMovimiento(@PathVariable Integer id, @RequestBody Movimiento movimientoDetails) {
        return movimientoServiceImp.updateMovimiento(id, movimientoDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Integer id) {
        movimientoServiceImp.deleteMovimiento(id);
    }


    @PostMapping("/reporte")
    public List<Movimiento> getMovementsReport(@RequestBody TransaccionRequest request) {
        return movimientoServiceImp.getMovementsReport(request);
    }
}