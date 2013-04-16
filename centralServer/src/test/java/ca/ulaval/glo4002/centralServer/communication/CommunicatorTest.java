package ca.ulaval.glo4002.centralServer.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.common.requestSender.POSTRequestSender;

public class CommunicatorTest {

    private static final String MESSAGE_KEY = "message";
    private static final String ADDRESS_KEY = "address";
    private static final CommunicationType COMMUNICATION_TYPE = CommunicationType.POLICE;
    private static final String AN_ADDRESS = "Address 1";
    private static final String A_MESSAGE = MESSAGE_KEY;

    @Mock
    private POSTRequestSender postRequestSender;

    @InjectMocks
    private Communicator communicator = new Communicator();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void callsSendPostRequestWhenSending() {
        User user = mock(User.class);
        doReturn(AN_ADDRESS).when(user).getAddress();

        communicator.sendMessageToEmergencyServer(COMMUNICATION_TYPE, user);

        verify(postRequestSender).sendRequest(anyString(), anyString());
    }

    @Test
    public void whenSendingWithAMessageThenPostRequestSendsAddressWithMessage() throws JSONException {
        User user = mock(User.class);
        doReturn(AN_ADDRESS).when(user).getAddress();
        String expectedMessage = createExpectedMessage();

        communicator.sendMessageToEmergencyServer(COMMUNICATION_TYPE, user, A_MESSAGE);

        verify(postRequestSender).sendRequest(anyString(), eq(expectedMessage.toString()));
    }

    private String createExpectedMessage() throws JSONException {
        JSONObject message = new JSONObject();
        message.put(ADDRESS_KEY, AN_ADDRESS);
        message.put(MESSAGE_KEY, A_MESSAGE);
        return message.toString();
    }

}
