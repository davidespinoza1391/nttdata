package com.nttdata.nttdata.repository;


import com.nttdata.nttdata.entity.Cliente;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByClienteId(String clienteId);

}