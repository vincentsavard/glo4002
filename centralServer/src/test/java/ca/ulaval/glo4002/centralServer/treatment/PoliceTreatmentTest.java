package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class PoliceTreatmentTest {

    private static final String A_GOOD_URL_ID = "20";
    private static final String A_WRONG_URL_ID = "13";

    @Mock
    private Communicator communicator;

    @InjectMocks
    private PoliceTreatment policeTreatment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UserDirectory mockedUserDirectory = mock(UserDirectory.class);
        UserDirectory.load(mockedUserDirectory);
    }

    @After
    public void tearDown() {
        UserDirectory.load(null);
    }

    @Test
    public void whenProcessingTheRequestWithAGoodUserIdThenCommunicatorSendsSomething()
            throws UserNotFoundException {
        int aGoodID = Integer.parseInt(A_GOOD_URL_ID);
        doReturn(true).when(UserDirectory.getInstance()).userExists(aGoodID);

        policeTreatment.processRequest(A_GOOD_URL_ID);

        verify(communicator).sendMessageToEmergencyServer(any(User.class));
    }

    @Test(expected = UserNotFoundException.class)
    public void whenProcessingTheRequestWithAWrongUserIdThenANotFoundUserExceptionIsThrown()
            throws UserNotFoundException {
        int aWrongID = Integer.parseInt(A_WRONG_URL_ID);
        doReturn(false).when(UserDirectory.getInstance()).userExists(aWrongID);

        policeTreatment.processRequest(A_WRONG_URL_ID);
    }

}
