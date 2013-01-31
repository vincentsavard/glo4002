package ca.ulaval.glo4002.emergencyServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class RequestTreatment {

	private String RESPONSE_TO_POST_REQUEST = "POST request received at ermergency server";

	@POST
	@Path("{path}")
	public String treatRequest(@PathParam("path") String path) {
		return RESPONSE_TO_POST_REQUEST;
	}

}
