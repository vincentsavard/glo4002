package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.treatment.UserRegistrar;

@Path("/register/")
public class RegisterResource {

    private static final int RESPONSE_OK = 200;

    private UserRegistrar userRegistrar = new UserRegistrar();

    @POST
    public Response registerUser(String userInformation) {
        Integer newUserID = userRegistrar.generateUserID();
        userRegistrar.registerUser(newUserID, userInformation);
        return Response.status(RESPONSE_OK).entity(newUserID.toString()).build();
    }

}
