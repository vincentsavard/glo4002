package ca.ulaval.glo4002.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.common.GETRequestSender;
import ca.ulaval.glo4002.common.POSTRequestSender;
import ca.ulaval.glo4002.communication.CommunicationUnit.CommunicationType;

public class CommunicationUnitTest {

    private static final int USER_ID = 1;
    private static final CommunicationType COMMUNICATION_TYPE = CommunicationType.POLICE;

    @Mock
    private POSTRequestSender postRequestSender;
    @Mock
    private GETRequestSender getRequestSender;

    @InjectMocks
    private CommunicationUnit communicationUnit = new CommunicationUnit(USER_ID, COMMUNICATION_TYPE);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void callsSendPostRequestWhenSendingWithAttributes() {
        HashMap<String, String> attributes = new HashMap<String, String>();

        communicationUnit.sendMessageToCentralServer(attributes);
        verify(postRequestSender).sendRequest(anyString(), anyString());
    }

    @Test
    public void callsSendGetRequestWhenSending() {
        communicationUnit.sendMessageToCentralServer();
        verify(getRequestSender).sendRequest(anyString());
    }
}
