package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;

@Path("/register/")
public class RegisterResource {

    private static final int RESPONSE_OK = 200;

    private UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    @POST
    public Response registerUser(String userInformation) {
<<<<<<< HEAD
        Integer newUserID = userDirectory.generateNewID();
        userDirectory.registerUser(newUserID, userInformation);
=======
        Integer newUserID = userRegistrar.generateUserID();

        userRegistrar.registerUser(newUserID, userInformation);
>>>>>>> d5a582e60bfdc29f41c5f2a3c4a5056bad0831ad
        return Response.status(RESPONSE_OK).entity(newUserID.toString()).build();
    }

}
