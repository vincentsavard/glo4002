package ca.ulaval.glo4002.centralServer.core.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.core.communication.Communicator;
import ca.ulaval.glo4002.centralServer.core.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.domain.treatment.EmergencyTreatment;
import ca.ulaval.glo4002.centralServer.domain.user.UserNotFoundException;

@Path("/client/")
public class EmergencyResource {

    private static final int RESPONSE_OK = 200;
    private static final int RESPONSE_HTTP_NOT_FOUND = 404;

    @POST
    @Path("{userID}/police")
    public Response askForPoliceAssistance(@Context Communicator communicator, @PathParam("userID") String userIDPassedByPOSTRequest) {
        try {
            EmergencyTreatment policeTreatment = new EmergencyTreatment(CommunicationType.POLICE, communicator);
            policeTreatment.processRequest(Integer.parseInt(userIDPassedByPOSTRequest));
        } catch (UserNotFoundException e) {
            return Response.status(RESPONSE_HTTP_NOT_FOUND).build();
        }
        return Response.status(RESPONSE_OK).build();
    }

    @POST
    @Path("{userID}/fire")
    public Response askForFireFighterAssistance(@Context Communicator communicator, @PathParam("userID") String userIDPassedByPOSTRequest, String zonePassedByPOSTRequest) {
        try {
            EmergencyTreatment firefighterTreatment = new EmergencyTreatment(CommunicationType.FIRE, communicator);
            firefighterTreatment.processRequest(Integer.parseInt(userIDPassedByPOSTRequest), zonePassedByPOSTRequest);
        } catch (UserNotFoundException e) {
            return Response.status(RESPONSE_HTTP_NOT_FOUND).build();
        }
        return Response.status(RESPONSE_OK).build();
    }

}
