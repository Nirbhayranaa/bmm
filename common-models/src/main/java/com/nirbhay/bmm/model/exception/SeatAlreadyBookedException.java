package com.nirbhay.bmm.model.exception;

/**
 * @author Nirbhay Rana
 */
public class SeatAlreadyBookedException extends RuntimeException{

    public SeatAlreadyBookedException(){
        super("Seat/s are already booked. Please try again.");
    }
}
