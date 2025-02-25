package com.nttdata.nttdata.services;

import com.nttdata.nttdata.model.RegistroRequest;
import com.nttdata.nttdata.model.RegistroResponse;

public interface RegistroService {
    RegistroResponse registerUser(RegistroRequest request);

}
