package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ca.ulaval.glo4002.centralServer.treatment.UserRegistrar;

@Path("/register/")
public class RegisterResource {

    private static final int RESPONSE_OK = 200;

    private UserRegistrar userRegistrar = new UserRegistrar();

    @POST
    public Response registerUser(JSONObject userInformation) throws JSONException {
        String address = userInformation.getString("address");
        Integer newUserID = userRegistrar.generateUserID();

        userRegistrar.registerUser(newUserID, address);
        return Response.status(RESPONSE_OK).entity(newUserID.toString()).build();
    }
}
