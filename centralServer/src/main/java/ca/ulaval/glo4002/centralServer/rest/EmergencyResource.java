package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.treatment.PoliceTreatment;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

@Path("/client/")
public class EmergencyResource {

    private static final int RESPONSE_OK = 200;
    private static final int HTTP_NOT_FOUND = 404;

    private PoliceTreatment policeTreatment = new PoliceTreatment();

    @POST
    @Path("{userID}/police")
    public Response askForPoliceAssistance(@PathParam("userID") String userIDPassedByGetRequest) {
        try {
            policeTreatment.processRequest(userIDPassedByGetRequest);
        } catch (UserNotFoundException userNotFoundException) {
            return Response.status(HTTP_NOT_FOUND).build();
        }
        return Response.status(RESPONSE_OK).build();
    }

}
