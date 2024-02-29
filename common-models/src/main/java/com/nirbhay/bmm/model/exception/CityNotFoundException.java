package com.nirbhay.bmm.model.exception;

/**
 * @author Nirbhay Rana
 */
public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(String s){
        super(s + "is not found");
    }
}
