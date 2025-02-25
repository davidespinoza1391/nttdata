package com.nttdata.nttdata.controller;

import com.nttdata.nttdata.model.RegistroRequest;
import com.nttdata.nttdata.model.RegistroResponse;
import com.nttdata.nttdata.services.imp.RegistroServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistroController {

    @Autowired
    private RegistroServiceImp registroServiceImp;

    @PostMapping("/registro")
    public RegistroResponse registerUser(@RequestBody RegistroRequest request) {
        return registroServiceImp.registerUser(request);
    }

}