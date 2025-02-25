package com.nttdata.nttdata.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistroResponse {
    private String message;

    public RegistroResponse(String message) {
        this.message = message;
    }

}