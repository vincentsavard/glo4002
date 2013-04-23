package ca.ulaval.glo4002.common.core.requestSender;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HTTPRequestSender {

    private static final String APPLICATION_TYPE = "application/json";
    private static final int RESPONSE_OK = 200;

    private String serverURL;
    private Client client;

    public HTTPRequestSender(String serverURL, int port) {
        this.serverURL = String.format("%s:%s", serverURL, port);
        client = Client.create();
    }

    private WebResource changeWebResource(String resource) {
        return client.resource(String.format("%s/%s", serverURL, resource));
    }

    private WebResource prepareRequest(String resource) {
        WebResource webResource = changeWebResource(resource);
        return webResource;
    }

    public String sendPOSTRequest(String resource, String messageToSend) {
        WebResource webResource = prepareRequest(resource);

        ClientResponse response = webResource.type(APPLICATION_TYPE).post(ClientResponse.class, messageToSend);

        treatAnswerFromRequest(response);
        return response.getEntity(String.class);
    }

    public String sendPOSTRequest(String resource) {
        WebResource webResource = prepareRequest(resource);

        ClientResponse response = webResource.type(APPLICATION_TYPE).post(ClientResponse.class);

        treatAnswerFromRequest(response);
        return response.getEntity(String.class);
    }

    public String sendGETRequest(String resource) {
        WebResource webResource = prepareRequest(resource);

        ClientResponse response = webResource.type(APPLICATION_TYPE).get(ClientResponse.class);

        treatAnswerFromRequest(response);
        return response.getEntity(String.class);
    }

    private void treatAnswerFromRequest(ClientResponse response) {
        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }
    }

    // For test purposes only
    protected HTTPRequestSender(String serverURL, int port, Client client) {
        this.serverURL = String.format("%s:%s", serverURL, port);
        this.client = client;
    }

}
