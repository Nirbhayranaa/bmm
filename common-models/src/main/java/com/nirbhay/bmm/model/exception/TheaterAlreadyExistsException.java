package com.nirbhay.bmm.model.exception;

public class TheaterAlreadyExistsException extends RuntimeException {
    
    public TheaterAlreadyExistsException(String message) {
        super(message);
    }
}
