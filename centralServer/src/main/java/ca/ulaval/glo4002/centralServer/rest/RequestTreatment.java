package ca.ulaval.glo4002.centralServer.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class RequestTreatment {

    private String RESPONSE_TO_POST_REQUEST = "POST request received at central server";
    private String EMERGENCY_URL = "http://localhost:8081/emergencyServer/";
    private String A_POST_REQUEST = "simple request";
    private HttpURLConnection emergencyUrlConnector;

    @POST
    @Path("{path}")
    public String treatRequest(@PathParam("path") String path)
            throws MalformedURLException, IOException {
        String emergencyAnswer = null;

        emergencyAnswer = sendingPostResquest(EMERGENCY_URL, A_POST_REQUEST);

        return RESPONSE_TO_POST_REQUEST + emergencyAnswer;
    }

    private String sendingPostResquest(String urlWherePostRequestIsSent,
            String request) throws MalformedURLException, IOException {
        preparePostRequestToEmergency();
        sendStringRequestToEmergency(request);
        String answer = retrieveAnswerFromEmergency();
        return answer;
    }

    private void sendStringRequestToEmergency(String requestToSend)
            throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(
                emergencyUrlConnector.getOutputStream());
        writer.write(requestToSend);
        writer.flush();
        writer.close();
    }

    private void preparePostRequestToEmergency() throws MalformedURLException,
            IOException {
        emergencyUrlConnector = (HttpURLConnection) new URL(EMERGENCY_URL)
                .openConnection();
        emergencyUrlConnector.setRequestMethod("POST");
        emergencyUrlConnector.setDoOutput(true);
    }

    private String retrieveAnswerFromEmergency() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                emergencyUrlConnector.getInputStream()));
        String answer = reader.readLine();
        reader.close();
        return answer;
    }

}
