package ca.ulaval.glo4002.communication;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.common.POSTRequestSender;

public class RegistrationCommunicationUnitTest {

    private static final String A_POST_RESPONSE = "125";

    @Mock
    private POSTRequestSender postRequestSender;

    @InjectMocks
    RegistrationCommunicationUnit registrationCommUnit;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canRetrieveUserIDafterMessageIsSent() {
        doReturn(A_POST_RESPONSE).when(postRequestSender).sendPostRequest(anyString(), anyString());
        registrationCommUnit.sendRegistrationRequest(new HashMap<String, String>());

        int userID = registrationCommUnit.retrieveUserID();

        assertEquals(Integer.parseInt(A_POST_RESPONSE), userID);
    }
}
