package com.github.damianmcdonald.errorhandling;

import java.util.UUID;

public class InformativeErrorException extends RuntimeException {

    final String errorReference;

    final Object[] params;

    public InformativeErrorException(String message, Throwable cause, Object[] params)
    {
        super(message, cause);
        this.errorReference = UUID.randomUUID().toString();
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public String getErrorReference() {
        return errorReference;
    }

}
