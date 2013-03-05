package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.treatment.PoliceTreatment;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

@Path("/client/")
public class EmergencyResource {

    private static final int OK = 200;
    private static final int ERROR = 1000;

    private PoliceTreatment policeTreatment;

    public EmergencyResource() {
        policeTreatment = new PoliceTreatment();
    }

    @GET
    @Path("{userId}/police")
    public Response askForPoliceAssistance(@PathParam("userId") String userIdPassedByGetRequest) {
        try {
            policeTreatment.processRequest(userIdPassedByGetRequest);
        } catch (UserNotFoundException userNotFoundException) {
            return Response.status(ERROR).build();
        }
        return Response.status(OK).build();
    }
}
