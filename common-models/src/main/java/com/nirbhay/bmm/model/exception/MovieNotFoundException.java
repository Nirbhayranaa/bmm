package com.nirbhay.bmm.model.exception;

/**
 * @author Nirbhay Rana
 */
public class MovieNotFoundException extends RuntimeException{
    String movieName;

    public MovieNotFoundException(String movieName){
        super(movieName + " is not found");
    }
}
