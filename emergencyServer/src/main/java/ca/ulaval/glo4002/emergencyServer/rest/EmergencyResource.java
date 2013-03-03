package ca.ulaval.glo4002.emergencyServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")
public class EmergencyResource {

    private static final String RESPONSE_TO_POST_REQUEST = "POST request received at emergency server";

    @POST
    @Path("/police/")
    public String treatRequest(final String helpRequesterInformations) {
        return RESPONSE_TO_POST_REQUEST;
    }
}
