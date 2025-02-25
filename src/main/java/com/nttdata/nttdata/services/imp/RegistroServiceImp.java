package com.nttdata.nttdata.services.imp;

import com.nttdata.nttdata.model.RegistroRequest;
import com.nttdata.nttdata.model.RegistroResponse;
import com.nttdata.nttdata.entity.Cliente;
import com.nttdata.nttdata.repository.ClienteRepository;

import com.nttdata.nttdata.services.RegistroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistroServiceImp implements RegistroService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public RegistroResponse registerUser(RegistroRequest request) {
        log.info("Datos registro"+request.getIdCliente());
        if (clienteRepository.findByClienteId(request.getIdCliente()) != null) {
            return new RegistroResponse("El usuario ya existe");
        }

        Cliente cliente = new Cliente();
        cliente.setClienteId(request.getIdCliente());
        cliente.setContrasena(request.getContrasena());
        cliente.setEstado(true);
        cliente.setNombre(request.getNombre());
        cliente.setGenero(request.getGenero());
        cliente.setEdad(request.getEdad());
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());

        clienteRepository.save(cliente);

        return new RegistroResponse("Usuario registrado correctamente");
    }

}
