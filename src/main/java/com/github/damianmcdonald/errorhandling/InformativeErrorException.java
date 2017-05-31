package com.github.damianmcdonald.errorhandling;

public class InformativeErrorException extends RuntimeException {

    public InformativeErrorException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
