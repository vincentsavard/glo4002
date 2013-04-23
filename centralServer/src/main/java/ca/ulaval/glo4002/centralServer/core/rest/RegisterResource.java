package ca.ulaval.glo4002.centralServer.core.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.domain.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.domain.user.UserDirectoryLocator;

@Path("/register/")
public class RegisterResource {

    private static final int RESPONSE_OK = 200;

    private UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    @POST
    public Response registerUser(String userInformation) {
        Integer newUserID = userDirectory.generateNewID();
        userDirectory.registerUser(newUserID, userInformation);
        return Response.status(RESPONSE_OK).entity(newUserID.toString()).build();
    }

}
