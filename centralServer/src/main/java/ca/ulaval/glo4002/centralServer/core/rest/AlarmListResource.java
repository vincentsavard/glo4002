package ca.ulaval.glo4002.centralServer.core.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ca.ulaval.glo4002.centralServer.domain.user.User;
import ca.ulaval.glo4002.centralServer.domain.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.domain.user.UserDirectoryLocator;

@Path("/alarm/")
public class AlarmListResource {

    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    @GET
    @Produces("application/json")
    @Path("{userID}")
    public User askForAlarmFromUser(@PathParam("userID") String userIDPassedByGetRequest) {
        int userID = Integer.parseInt(userIDPassedByGetRequest);
        return userDirectory.obtainUser(userID);
    }

}
