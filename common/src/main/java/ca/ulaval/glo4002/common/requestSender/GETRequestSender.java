package ca.ulaval.glo4002.common.requestSender;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GETRequestSender extends HTTPRequestSender {
    
    public GETRequestSender(int port) {
        super(port);
    }

    public String sendRequest(String resource) {
        WebResource webResource = prepareRequest(resource);

        ClientResponse response = webResource.type(APPLICATION_TYPE).get(ClientResponse.class);

        treatAnswerFromRequest(response);
        return response.getEntity(String.class);
    }

    // For test purposes only
    protected GETRequestSender(int port, Client client) {
        super(port, client);
    }

}