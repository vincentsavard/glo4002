package ca.ulaval.glo4002.common.requestSender;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class GETRequestSenderTest {

    private static final int AN_HTTP_ERROR_CODE = 500;
    private static final int A_PORT = 8080;

    private Client client;
    private GETRequestSender getRequestSender;

    @Before
    public void initGETRequestSender() {
        client = mock(Client.class);
        getRequestSender = new GETRequestSender(A_PORT, client);
    }

    @Test(expected = HTTPException.class)
    public void throwsHTTPExceptionWhenResponseIsNotOk() {
        WebResource resource = mock(WebResource.class);
        Builder builder = mock(Builder.class);
        ClientResponse clientResponse = mock(ClientResponse.class);

        doReturn(resource).when(client).resource(anyString());
        doReturn(builder).when(resource).type(anyString());
        doReturn(clientResponse).when(builder).get(ClientResponse.class);
        doReturn(AN_HTTP_ERROR_CODE).when(clientResponse).getStatus();

        getRequestSender.sendRequest(anyString());
    }

}
