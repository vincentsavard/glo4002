package ca.ulaval.glo4002.common;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sun.jersey.api.client.WebResource;

public class HTTPRequestSenderTest {

    private static final String INVALID_URL = "@!#!(%@*#$)(!)@#~~{}>>..";
    private static final String A_MESSAGE = "message";

    @Mock
    private WebResource resource;

    @InjectMocks
    private POSTRequestSender postRequestSender;

    @Before
    public void initHomeConnectionHandler() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionWhenURLIsInvalid() {
        postRequestSender.sendRequest(INVALID_URL, A_MESSAGE);
    }
}
