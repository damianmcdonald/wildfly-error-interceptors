package com.github.damianmcdonald.errorhandling;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public class ErrorMessageController {
    @Inject
    ErrorMessageService errorMessageService;

    @POST
    @Path("/working")
    @Produces("application/json")
    public String workingExampke() {
        return "{\"result\":\"" + errorMessageService.workingExample() + "\"}";
    }

    @POST
    @Path("/failing")
    @Produces("application/json")
    public String failingExample() {
        return "{\"result\":\"" + errorMessageService.failingExample() + "\"}";
    }

}
