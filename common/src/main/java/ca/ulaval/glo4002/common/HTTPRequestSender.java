package ca.ulaval.glo4002.common;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public abstract class HTTPRequestSender {
    
    protected static final String APPLICATION_TYPE = "application/json";
    protected static final String SERVER_URL = "http://localhost:8080/";
    protected static final int RESPONSE_OK = 200;

    protected WebResource webResource;

    public HTTPRequestSender() {
        Client client = Client.create();
        webResource = client.resource(SERVER_URL);
    }

    protected HTTPRequestSender(final WebResource ressource) {
        webResource = ressource;
    }

    protected void changeWebResource(String resource) {
        try {
            URI resourceURL = new URI(String.format("%s/%s", SERVER_URL, resource));
            webResource.uri(resourceURL);
        } catch (URISyntaxException e) {
            // It is currently unrecoverable because the URLs are hard-coded. If
            // we can't create it here, then nobody can.
            throw new RuntimeException("There was an unrecoverable error trying to create a new resource URL");
        }
    }
}
