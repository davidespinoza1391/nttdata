package com.nttdata.nttdata.repository;

import com.nttdata.nttdata.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id = :idCuenta AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findByAccountAndDateRange(
            @Param("idCuenta") Integer idCuenta,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id = :idCuenta ORDER BY m.fecha ASC")
    List<Movimiento> findByCuentaId(@Param("idCuenta") Integer idCuenta);

}