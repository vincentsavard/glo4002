package ca.ulaval.glo4002.centralServer.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.common.requestSender.POSTRequestSender;

public class CommunicatorTest {

    private static final CommunicationType COMMUNICATION_TYPE = CommunicationType.POLICE;
    private static final String AN_ADDRESS = "Address 1";

    @Mock
    private POSTRequestSender postRequestSender;

    @InjectMocks
    private Communicator communicator = new Communicator(COMMUNICATION_TYPE);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void callsSendPostRequestWhenSending() {
        User user = mock(User.class);
        doReturn(AN_ADDRESS).when(user).getAddress();

        communicator.sendMessageToEmergencyServer(user);

        verify(postRequestSender).sendRequest(anyString(), anyString());
    }

}
