package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.treatment.UserRegistrar;

@Path("/register/")
public class RegisterResource {

    private UserRegistrar userRegistrar = new UserRegistrar();

    @POST
    public Response registerUser(String userInformation) {
        Integer newUserId = userRegistrar.generateUserID();
        userRegistrar.registerUser(newUserId, userInformation);
        return Response.status(200).entity(newUserId.toString()).build();
    }
}
