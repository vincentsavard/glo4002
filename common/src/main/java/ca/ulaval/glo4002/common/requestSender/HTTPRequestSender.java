package ca.ulaval.glo4002.common.requestSender;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public abstract class HTTPRequestSender {

    protected static final String APPLICATION_TYPE = "application/json";
    protected static final int RESPONSE_OK = 200;

    protected String serverURL = "http://localhost";
    protected int port;
    protected Client client;

    public HTTPRequestSender(int port) {
        serverURL = String.format("%s:%s", serverURL, port);
        client = Client.create();
    }

    protected HTTPRequestSender(int port, Client client) {
        serverURL = String.format("%s:%s", serverURL, port);
        this.client = client;
    }

    protected WebResource changeWebResource(String resource) {
        return client.resource(String.format("%s/%s", serverURL, resource));
    }

    protected WebResource prepareRequest(String resource) {
        WebResource webResource = changeWebResource(resource);
        return webResource;
    }

    protected void treatAnswerFromRequest(ClientResponse response) {
        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }
    }

}
