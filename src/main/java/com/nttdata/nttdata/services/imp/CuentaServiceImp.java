package com.nttdata.nttdata.services.imp;

import com.nttdata.nttdata.entity.Cliente;
import com.nttdata.nttdata.entity.Cuenta;
import com.nttdata.nttdata.exception.CustomException;
import com.nttdata.nttdata.repository.ClienteRepository;
import com.nttdata.nttdata.repository.CuentaRepository;
import com.nttdata.nttdata.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImp implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    // Crear cuenta
    public Cuenta createCuenta(Cuenta cuenta) {
        if (cuentaRepository.existsByNumeroCuenta(cuenta.getNumeroCuenta())) {
            throw new CustomException("Cuenta ya existe");
        }
        // Aquí buscamos el Cliente a partir del clienteId
        Cliente cliente = clienteRepository.findByClienteId(String.valueOf(cuenta.getCliente().getClienteId()));
        if (cliente == null) {
            throw new CustomException("Cliente no encontrado");
        }

        // Asignamos el Cliente a la cuenta
        cuenta.setCliente(cliente);
        return cuentaRepository.save(cuenta);
    }

    // Obtener todas las cuentas
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    // Actualizar cuenta
    public Cuenta updateCuenta(Integer id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new CustomException("No existe la cuenta"));

        cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.getEstado());
        cuenta.setCliente(cuentaDetails.getCliente());

        return cuentaRepository.save(cuenta);
    }

    // Eliminar  cuenta
    public void deleteCuenta(Integer id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new CustomException("No existe la cuenta"));
        cuentaRepository.delete(cuenta);
    }
}
