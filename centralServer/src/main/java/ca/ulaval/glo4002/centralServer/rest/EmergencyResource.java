package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.treatment.FirefighterTreatment;
import ca.ulaval.glo4002.centralServer.treatment.PoliceTreatment;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

@Path("/client/")
public class EmergencyResource {

    private static final int RESPONSE_OK = 200;
    private static final int HTTP_NOT_FOUND = 404;

    @GET
    @Path("{userID}/police")
    public Response askForPoliceAssistance(@Context Communicator communicator, @PathParam("userID") String userIDPassedByGetRequest) {
        try {
            PoliceTreatment policeTreatment = new PoliceTreatment(communicator);
            policeTreatment.processRequest(userIDPassedByGetRequest);
        } catch (UserNotFoundException userNotFoundException) {
            return Response.status(HTTP_NOT_FOUND).build();
        }
        return Response.status(RESPONSE_OK).build();
    }

    @GET
    @Path("{userID}/firefighter/{zone}")
    public Response askForFireFighterAssistance(@Context Communicator communicator, @PathParam("userID") String userIDPassedByGetRequest, @PathParam("zone") String zonePassedByGetRequest) {
        try {
            FirefighterTreatment firefighterTreatment = new FirefighterTreatment(communicator);
            firefighterTreatment.processRequest(userIDPassedByGetRequest, zonePassedByGetRequest);
        } catch (UserNotFoundException userNotFoundException) {
            return Response.status(HTTP_NOT_FOUND).build();
        }
        return Response.status(RESPONSE_OK).build();
    }

}
