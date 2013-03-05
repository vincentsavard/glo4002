package ca.ulaval.glo4002.emergencyServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

@Path("/")
public class EmergencyResource {

    private static final String RESPONSE_TO_POST_REQUEST = "POST request received at emergency server";

    @POST
    @Path("police/")
    public String treatRequest(String helpRequesterInformations) {
        EmergencyServer.called = true;
        return RESPONSE_TO_POST_REQUEST;
    }
}
