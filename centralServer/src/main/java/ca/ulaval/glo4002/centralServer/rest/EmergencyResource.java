package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import ca.ulaval.glo4002.centralServer.treatment.PoliceTreatment;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

@Path("/client/")
public class EmergencyResource {

    private static final int OK = 0;
    private static final int ERROR = 1;

    private PoliceTreatment policeTreatment;

    public EmergencyResource() {
        policeTreatment = new PoliceTreatment();
    }

    @GET
    @Path("{userId}/Police")
    public int askForPoliceAssistance(@PathParam("userId") final String userIdPassedByGetRequest) {
        try {
            policeTreatment.processRequest(userIdPassedByGetRequest);
        } catch (UserNotFoundException userNotFoundException) {
            return ERROR;
        }
        return OK;
    }
}
