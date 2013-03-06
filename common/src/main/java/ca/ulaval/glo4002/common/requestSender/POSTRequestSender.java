package ca.ulaval.glo4002.common.requestSender;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class POSTRequestSender extends HTTPRequestSender {

    public POSTRequestSender(int port) {
        super(port);
    }

    protected POSTRequestSender(int port, Client client) {
        super(port, client);
    }

    public String sendRequest(String resource, String messageToSend) {
        WebResource webResource = prepareRequest(resource);

        ClientResponse response = webResource.type(APPLICATION_TYPE).post(ClientResponse.class, messageToSend);

        treatAnswerFromRequest(response);
        return response.getEntity(String.class);
    }

}