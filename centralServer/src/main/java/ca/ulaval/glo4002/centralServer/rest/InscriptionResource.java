package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import ca.ulaval.glo4002.centralServer.treatment.UserRegistrar;

@Path("/inscription/")
public class InscriptionResource {

    private UserRegistrar userRegistrar = new UserRegistrar();

    @POST
    public int registerUser(final String userInformation) {
        int newUserId = userRegistrar.generateUserID();
        userRegistrar.registerUser(newUserId, userInformation);
        return newUserId;
    }
}
