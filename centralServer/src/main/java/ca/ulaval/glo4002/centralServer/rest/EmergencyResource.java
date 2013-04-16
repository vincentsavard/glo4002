package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.treatment.EmergencyTreatment;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

@Path("/client/")
public class EmergencyResource {

    private static final int RESPONSE_OK = 200;
    private static final int HTTP_NOT_FOUND = 404;

    @POST
    @Path("{userID}/police")
    public Response askForPoliceAssistance(@Context Communicator communicator, @PathParam("userID") String userIDPassedByGetRequest) {
        try {
            EmergencyTreatment policeTreatment = new EmergencyTreatment(CommunicationType.POLICE, communicator);
            policeTreatment.processRequest(userIDPassedByGetRequest);
        } catch (UserNotFoundException userNotFoundException) {
            return Response.status(HTTP_NOT_FOUND).build();
        }
        return Response.status(RESPONSE_OK).build();
    }

    @POST
    @Path("{userID}/firefighter")
    public Response askForFireFighterAssistance(@Context Communicator communicator, @PathParam("userID") String userIDPassedByGetRequest, String zonePassedByGetRequest) {
        try {
            EmergencyTreatment firefighterTreatment = new EmergencyTreatment(CommunicationType.FIRE, communicator);
            firefighterTreatment.processRequest(userIDPassedByGetRequest, zonePassedByGetRequest);
        } catch (UserNotFoundException userNotFoundException) {
            return Response.status(HTTP_NOT_FOUND).build();
        }
        return Response.status(RESPONSE_OK).build();
    }

}
