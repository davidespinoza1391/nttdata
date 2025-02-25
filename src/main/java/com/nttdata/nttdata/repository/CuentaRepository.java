package com.nttdata.nttdata.repository;


import com.nttdata.nttdata.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    boolean existsByNumeroCuenta(String accountNumber);
    Optional<Cuenta> findByNumeroCuenta(String accountNumber);
}