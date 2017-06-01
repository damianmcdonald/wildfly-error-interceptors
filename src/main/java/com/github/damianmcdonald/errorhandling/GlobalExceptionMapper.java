package com.github.damianmcdonald.errorhandling;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<InformativeErrorException> {

    @Override
    public Response toResponse(InformativeErrorException ex) {
        ex.printStackTrace();
        final String errorMessage = String.format("{\"result\":\"Error: %s with Unique Reference ID: %s\"}", ex.getMessage(), ex.getErrorReference());
        return Response.ok().entity(errorMessage).build();
    }

}
