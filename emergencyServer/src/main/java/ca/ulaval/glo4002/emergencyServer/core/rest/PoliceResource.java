package ca.ulaval.glo4002.emergencyServer.core.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

@Path("/police/")
public class PoliceResource {

    private static final int RESPONSE_OK = 200;

    @POST
    public Response treatRequest(String helpRequesterInformations) {
        EmergencyServer.policeWasCalled = true;
        return Response.status(RESPONSE_OK).build();
    }

}
