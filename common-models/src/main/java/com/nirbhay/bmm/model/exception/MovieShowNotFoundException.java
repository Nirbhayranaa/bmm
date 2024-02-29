package com.nirbhay.bmm.model.exception;

/**
 * @author Nirbhay Rana
 */
public class MovieShowNotFoundException extends RuntimeException{


    public MovieShowNotFoundException(long movieShowId) {
        super("Movie show for " +movieShowId + " is not found");
    }

    public MovieShowNotFoundException(String s) {
        super(s);
    }
}
