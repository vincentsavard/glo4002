package ca.ulaval.glo4002.emergencyServer.core.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

@Path("/fire/")
public class FireResource {

    private static final int RESPONSE_OK = 200;

    @POST
    public Response treatRequest(String helpRequesterInformations) throws JSONException {
        JSONObject jsonObject = new JSONObject(helpRequesterInformations);
        EmergencyServer.fireFightersWereCalled = true;
        EmergencyServer.calledZone = jsonObject.getInt("message");

        return Response.status(RESPONSE_OK).build();
    }

}
