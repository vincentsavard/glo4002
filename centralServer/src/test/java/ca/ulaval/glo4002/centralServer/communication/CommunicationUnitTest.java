package ca.ulaval.glo4002.centralServer.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.common.POSTRequestSender;

public class CommunicationUnitTest {

    private static final CommunicationType COMMUNICATION_TYPE = CommunicationType.POLICE;
    private static final String AN_ADDRESS = "Address 1";

    @Mock
    private POSTRequestSender postRequestSender;

    @InjectMocks
    private CommunicationUnit communicationUnit = new CommunicationUnit(COMMUNICATION_TYPE);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void callsSendPostRequestWhenSending() {
        User user = mock(User.class);
        doReturn(AN_ADDRESS).when(user).getAddress();

        communicationUnit.sendMessageToEmergencyServer(user);

        verify(postRequestSender).sendRequest(anyString(), anyString());
    }

}
