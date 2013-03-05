package ca.ulaval.glo4002.common;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GETRequestSender extends HTTPRequestSender {

    public GETRequestSender(int port) {
        super(port);
    }

    protected GETRequestSender(WebResource ressource) {
        super(ressource);
    }

    public String sendRequest(String resource) {
        changeWebResource(resource);
        System.out.println(resource);
        ClientResponse response = webResource.type(APPLICATION_TYPE).get(ClientResponse.class);

        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }

        return response.toString();
    }
}