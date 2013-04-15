package ca.ulaval.glo4002.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.common.requestSender.POSTRequestSender;
import ca.ulaval.glo4002.communication.Communicator.TargetResource;

public class CommunicatorTest {

    private static final String A_MESSAGE = "Message";
    private static final String AN_ADDRESS = "123 rue ville";
    private static final TargetResource A_POLICE_TARGET_RESOURCE = TargetResource.POLICE;
    private static final TargetResource A_REGISTRATION_TARGET_RESOURCE = TargetResource.REGISTRATION;

    @Mock
    private POSTRequestSender postRequestSender;

    private Communicator communicator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn("1").when(postRequestSender).sendRequest(anyString(), anyString());
        communicator = new Communicator(AN_ADDRESS, postRequestSender);
    }

    @Test
    public void callsSendPostRequestWhenSendingWithMessage() {
        String urlResource = communicator.generateResourceURL(A_POLICE_TARGET_RESOURCE);

        communicator.sendMessageToCentralServer(A_POLICE_TARGET_RESOURCE, A_MESSAGE);
        verify(postRequestSender).sendRequest(urlResource, A_MESSAGE);
    }

    @Test
    public void registersHouseWhenCommunicatorIsCreated() {
        String urlResource = communicator.generateResourceURL(A_REGISTRATION_TARGET_RESOURCE);
        verify(postRequestSender).sendRequest(urlResource, AN_ADDRESS);
    }

    @Test
    public void callsSendPostRequestWhenSending() {
        communicator.sendMessageToCentralServer(A_POLICE_TARGET_RESOURCE);
        verify(postRequestSender).sendRequest(anyString());
    }

}