package ca.ulaval.glo4002.common.requestSender;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class HTTPRequestSenderTest {

    private static final String A_MESSAGE = "message";
    private static final int AN_HTTP_ERROR_CODE = 500;
    private static final int A_PORT = 8080;

    private Client client;
    private WebResource resource;
    private Builder builder;
    private ClientResponse clientResponse;

    private HTTPRequestSender HTTPRequestSender;

    @Before
    public void initHTTPRequestSender() {
        client = mock(Client.class);
        resource = mock(WebResource.class);
        builder = mock(Builder.class);
        clientResponse = mock(ClientResponse.class);
        HTTPRequestSender = new HTTPRequestSender(A_PORT, client);
    }

    @Test(expected = HTTPException.class)
    public void throwsHTTPExceptionWhenResponseFromPOSTRequestIsNotOk() {
        doReturn(resource).when(client).resource(anyString());
        doReturn(builder).when(resource).type(anyString());
        doReturn(clientResponse).when(builder).post(ClientResponse.class, A_MESSAGE);
        doReturn(AN_HTTP_ERROR_CODE).when(clientResponse).getStatus();

        HTTPRequestSender.sendPOSTRequest(anyString(), A_MESSAGE);
    }

    @Test(expected = HTTPException.class)
    public void throwsHTTPExceptionWhenResponseFromEmptyPOSTRequestIsNotOk() {
        WebResource resource = mock(WebResource.class);
        clientResponse = mock(ClientResponse.class);
        Builder builder = mock(Builder.class);
        ClientResponse clientResponse = mock(ClientResponse.class);

        doReturn(resource).when(client).resource(anyString());
        doReturn(builder).when(resource).type(anyString());
        doReturn(clientResponse).when(builder).post(ClientResponse.class);
        doReturn(AN_HTTP_ERROR_CODE).when(clientResponse).getStatus();

        HTTPRequestSender.sendPOSTRequest(anyString());
    }

    @Test(expected = HTTPException.class)
    public void throwsHTTPExceptionWhenResponseGETRequestIsNotOk() {
        WebResource resource = mock(WebResource.class);
        Builder builder = mock(Builder.class);
        ClientResponse clientResponse = mock(ClientResponse.class);

        doReturn(resource).when(client).resource(anyString());
        doReturn(builder).when(resource).type(anyString());
        doReturn(clientResponse).when(builder).get(ClientResponse.class);
        doReturn(AN_HTTP_ERROR_CODE).when(clientResponse).getStatus();

        HTTPRequestSender.sendGETRequest(anyString());
    }

}
