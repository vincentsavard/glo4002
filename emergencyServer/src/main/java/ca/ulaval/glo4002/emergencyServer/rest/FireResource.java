package ca.ulaval.glo4002.emergencyServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;
import org.json.JSONException;

import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

@Path("/fire/")
public class FireResource {

    private static final String RESPONSE_TO_POST_REQUEST = "POST request received at emergency server";
    private static final int RESPONSE_OK = 200;

    @POST
    public Response treatRequest(String helpRequesterInformations) throws JSONException, org.codehaus.jettison.json.JSONException {
        JSONObject jsonObject = new JSONObject(helpRequesterInformations);
        EmergencyServer.fireFightersWereCalled = true;
        EmergencyServer.calledZone = jsonObject.getInt("message");

        return Response.status(RESPONSE_OK).build();
    }

}
