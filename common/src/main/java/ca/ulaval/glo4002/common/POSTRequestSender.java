package ca.ulaval.glo4002.common;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class POSTRequestSender {

    private static final String SERVER_URL = "http://localhost:8080/";
    private static final int RESPONSE_OK = 200;

    private WebResource webResource;

    public POSTRequestSender() {
        Client client = Client.create();
        webResource = client.resource(SERVER_URL);
    }

    protected POSTRequestSender(final WebResource ressource) {
        webResource = ressource;
    }

    public String sendPostRequest(final String resource, final String messageToSend) throws RuntimeException {
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, messageToSend);
        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }
        return response.toString();
    }
}