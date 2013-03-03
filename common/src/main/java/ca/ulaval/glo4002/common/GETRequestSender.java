package ca.ulaval.glo4002.common;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GETRequestSender extends HTTPRequestSender {
    
    public GETRequestSender() {
        super();
    }

    protected GETRequestSender(final WebResource ressource) {
        super(ressource);
    }

    public String sendRequest(final String resource) {
        changeWebResource(resource);
        ClientResponse response = webResource.type(APPLICATION_TYPE).get(ClientResponse.class);

        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }

        return response.toString();
    }
}