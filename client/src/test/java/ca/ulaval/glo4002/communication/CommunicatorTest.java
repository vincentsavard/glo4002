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
import ca.ulaval.glo4002.communication.Communicator.TargetResource;
import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class CommunicatorTest {

    private static final int USER_ID = 1;
    private static final TargetResource A_VALID_TARGET_RESOURCE = TargetResource.POLICE;

    @Mock
    private POSTRequestSender postRequestSender;

    @Mock
    private GETRequestSender getRequestSender;

    @Mock
    private JSONMessageEncoder messageEncoder;

    @InjectMocks
    private Communicator communicator = new Communicator(USER_ID);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void callsSendPostRequestWhenSendingWithAttributes() {
        communicator.sendMessageToCentralServer(any(HashMap.class), A_VALID_TARGET_RESOURCE);
        verify(postRequestSender).sendRequest(anyString(), anyString());
    }

    @Test
    public void callsSendGetRequestWhenSending() {
        communicator.sendMessageToCentralServer(A_VALID_TARGET_RESOURCE);
        verify(getRequestSender).sendRequest(anyString());
    }

}