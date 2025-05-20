package com.estapar.estacionamento.exception;

public class GarageLoadException extends RuntimeException {
    public GarageLoadException(String message) {
        super(message);
    }

    public GarageLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
