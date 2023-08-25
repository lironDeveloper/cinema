package com.cinema.galaxy.exceptions;

public class UniqueException extends RuntimeException{
    public UniqueException(String message){
        super(message);
    }

    public UniqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
