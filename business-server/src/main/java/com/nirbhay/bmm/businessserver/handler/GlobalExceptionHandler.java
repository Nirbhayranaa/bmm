package com.nirbhay.bmm.businessserver.handler;

import com.nirbhay.bmm.model.exception.InternalServerException;
import com.nirbhay.bmm.model.exception.MovieShowNotFoundException;
import com.nirbhay.bmm.model.exception.SeatAlreadyBookedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<String> handleSeatAlreadyBookedException(SeatAlreadyBookedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<String> handleSeatAlreadyBookedException(InternalServerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieShowNotFoundException.class)
    public ResponseEntity<String> handleSeatAlreadyBookedException(MovieShowNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
