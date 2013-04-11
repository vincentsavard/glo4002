package ca.ulaval.glo4002.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.common.requestSender.HTTPRequestSender;
import ca.ulaval.glo4002.communication.Communicator.TargetResource;
import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class CommunicatorTest {

    private static final String AN_ADDRESS = "123 rue ville";
    private static final TargetResource A_VALID_TARGET_RESOURCE = TargetResource.POLICE;

    @Mock
    private HTTPRequestSender requestSender;

    @Mock
    private JSONMessageEncoder messageEncoder;

    private Communicator communicator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn("1").when(requestSender).sendPOSTRequest(anyString(), anyString());
        communicator = new Communicator(AN_ADDRESS, requestSender, messageEncoder);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void callsSendPostRequestWhenSendingWithAttributes() {
        String urlResource = communicator.generateResourceURL(A_VALID_TARGET_RESOURCE);

        communicator.sendMessageToCentralServer(any(HashMap.class), A_VALID_TARGET_RESOURCE);
        verify(requestSender).sendPOSTRequest(eq(urlResource), anyString());
    }

    @Test
    public void callsSendGetRequestWhenSending() {
        communicator.sendMessageToCentralServer(A_VALID_TARGET_RESOURCE);
        verify(requestSender).sendPOSTRequest(anyString());
    }

}