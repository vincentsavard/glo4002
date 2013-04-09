package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.treatment.AlarmListTreatment;

@Path("/alarm/")
public class AlarmListResource {

    private static final int RESPONSE_OK = 200;

    private AlarmListTreatment alarmListTreatment = new AlarmListTreatment();

    @POST
    @Path("{userID}")
    public Response askForAlarmFromUser(@PathParam("userID") String userIDPassedByGetRequest) {
        String log = alarmListTreatment.retrieveLogFromUser(userIDPassedByGetRequest);
        return Response.status(RESPONSE_OK).entity(log).build();
    }

}
