package com.nttdata.nttdata.services.imp;

import com.nttdata.nttdata.model.MovimientoRequest;
import com.nttdata.nttdata.model.TransaccionRequest;
import com.nttdata.nttdata.entity.Cuenta;
import com.nttdata.nttdata.entity.Movimiento;
import com.nttdata.nttdata.exception.CustomException;
import com.nttdata.nttdata.repository.CuentaRepository;
import com.nttdata.nttdata.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovimientoServiceImp {

    public static final String DEPOSITO = "DEPOSITO";
    public static final String RETIRO = "RETIRO";

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento createMovimiento(MovimientoRequest movimientoRequest) {

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimientoRequest.getNumeroCuenta())
                .orElseThrow(() -> new CustomException("Cuenta no encontrada"));

        if (!Boolean.TRUE.equals(cuenta.getEstado())) {
            throw new CustomException("La cuenta se encuentra inactiva");
        }

        if (!movimientoRequest.getTipoMovimiento().equalsIgnoreCase(DEPOSITO) &&
                !movimientoRequest.getTipoMovimiento().equalsIgnoreCase(RETIRO)) {
            throw new CustomException("Tipo de movimiento no válido.");
        }

        BigDecimal nuevoSaldo = getBigDecimal(movimientoRequest, cuenta);


        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);


        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento(movimientoRequest.getTipoMovimiento());
        movimiento.setValor(movimientoRequest.getValor());
        movimiento.setSaldo(nuevoSaldo);

        movimiento.setFecha(movimientoRequest.getFecha() != null ? movimientoRequest.getFecha() : LocalDate.now());
        movimiento.setCuenta(cuenta);

        return movimientoRepository.save(movimiento);
    }

    private static BigDecimal getBigDecimal(MovimientoRequest movimientoRequest, Cuenta cuenta) {
        BigDecimal nuevoSaldo;
        if (movimientoRequest.getTipoMovimiento().equalsIgnoreCase(RETIRO)) {
            nuevoSaldo = cuenta.getSaldoInicial().subtract(movimientoRequest.getValor().abs());
        } else {
            nuevoSaldo = cuenta.getSaldoInicial().add(movimientoRequest.getValor());
        }

        if (movimientoRequest.getTipoMovimiento().equalsIgnoreCase(RETIRO) && nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomException("Saldo no disponible");
        }
        return nuevoSaldo;
    }

    public List<Movimiento> getMovimientosByCuenta(Integer cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }

    @Transactional
    public void deleteMovimiento(Integer movimientoId) {
        Movimiento movimiento = movimientoRepository.findById(movimientoId)
                .orElseThrow(() -> new CustomException("Movimiento no encontrado"));

        Cuenta cuenta = movimiento.getCuenta();

        List<Movimiento> movimientos = movimientoRepository.findByCuentaId(cuenta.getId());

        if (movimientos.isEmpty()) {
            throw new CustomException("No hay movimientos registrados para esta cuenta");
        }

        Movimiento ultimoMovimiento = movimientos.get(movimientos.size() - 1);

        if (!ultimoMovimiento.getId().equals(movimientoId)) {
            throw new CustomException("Solo se puede eliminar el último movimiento registrado");
        }


        cuenta.setSaldoInicial(cuenta.getSaldoInicial().subtract(movimiento.getValor()));
        cuentaRepository.save(cuenta);


        movimientoRepository.delete(movimiento);
    }

    public Movimiento updateMovimiento(Integer id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Movimiento no encontrado"));

        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());
        movimiento.setCuenta(movimientoDetails.getCuenta());

        return movimiento;
    }

    public List<Movimiento> getMovementsReport(TransaccionRequest request) {
        return movimientoRepository.findByAccountAndDateRange(
                request.getIdCuenta(),
                request.getFechaInicio(),
                request.getFechaFin()
        );
    }
}
