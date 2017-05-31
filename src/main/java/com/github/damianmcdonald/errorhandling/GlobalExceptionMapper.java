package com.github.damianmcdonald.errorhandling;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        ex.printStackTrace();
        final String errorMessage =  "{\"result\":\"" + ex.getMessage() + "\"}";
        return Response.ok().entity(errorMessage).build();
    }

}
