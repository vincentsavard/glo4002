package ca.ulaval.glo4002.common.requestSender;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GETRequestSender extends HTTPRequestSender {

    public GETRequestSender(int port) {
        super(port);
    }

    protected GETRequestSender(int port, Client client) {
        super(port, client);
    }

    public String sendRequest(String resource) {
        WebResource webResource;
        webResource = changeWebResource(resource);

        ClientResponse response = webResource.type(APPLICATION_TYPE).get(ClientResponse.class);

        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }

        return response.toString();
    }

}