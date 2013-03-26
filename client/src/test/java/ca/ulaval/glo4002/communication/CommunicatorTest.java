package ca.ulaval.glo4002.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.common.requestSender.GETRequestSender;
import ca.ulaval.glo4002.common.requestSender.POSTRequestSender;
import ca.ulaval.glo4002.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class CommunicatorTest {

    private static final int USER_ID = 1;
    private static final CommunicationType COMMUNICATION_TYPE = CommunicationType.POLICE;

    @Mock
    private POSTRequestSender postRequestSender;

    @Mock
    private GETRequestSender getRequestSender;

    @Mock
    private JSONMessageEncoder messageEncoder;

    @InjectMocks
    private Communicator communicator = new Communicator(USER_ID, COMMUNICATION_TYPE);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void callsSendPostRequestWhenSendingWithAttributes() {
        communicator.sendMessageToCentralServer(any(HashMap.class));
        verify(postRequestSender).sendRequest(anyString(), anyString());
    }

    @Test
    public void callsSendGetRequestWhenSending() {
        communicator.sendMessageToCentralServer();
        verify(getRequestSender).sendRequest(anyString());
    }

}