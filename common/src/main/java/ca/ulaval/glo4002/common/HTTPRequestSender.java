package ca.ulaval.glo4002.common;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public abstract class HTTPRequestSender {

    protected static final String APPLICATION_TYPE = "application/json";
    protected String serverURL = "http://localhost";
    protected static final int RESPONSE_OK = 200;

    protected int port;

    protected WebResource webResource;

    public HTTPRequestSender(int port) {
        serverURL = String.format("%s:%s", serverURL, port);
        Client client = Client.create();
        webResource = client.resource(serverURL);
    }

    protected HTTPRequestSender(WebResource ressource) {
        webResource = ressource;
    }

    protected void changeWebResource(String resource) {
        Client client = Client.create();
        webResource = client.resource(String.format("%s/%s", serverURL, resource));
        System.out.println(webResource.toString());
    }
}
