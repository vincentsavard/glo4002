package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class FirefighterTreatmentTest {

    private static final String A_GOOD_URL_ID = "20";
    private static final String A_ZONE = "1";
    private static final String A_WRONG_URL_ID = "13";
    private static final CommunicationType COMMUNICATION_TYPE = CommunicationType.FIRE;

    @Mock
    private User user;

    @Mock
    private Communicator communicator;

    @Mock
    private UserDirectory userDirectory;

    @InjectMocks
    private FirefighterTreatment firefighterTreatment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenProcessingTheRequestWithAGoodUserIDThenCommunicatorSendsSomething() {
        int aGoodID = Integer.parseInt(A_GOOD_URL_ID);
        int aZone = Integer.parseInt(A_ZONE);
        doReturn(true).when(userDirectory).userExists(aGoodID);
        doReturn(user).when(userDirectory).obtainUser(aGoodID);

        firefighterTreatment.processRequest(A_GOOD_URL_ID, A_ZONE);

        verify(communicator).sendMessageToEmergencyServer(COMMUNICATION_TYPE, user, aZone);
    }

    @Test(expected = UserNotFoundException.class)
    public void whenProcessingTheRequestWithAWrongUserIDThenANotFoundUserExceptionIsThrown() throws UserNotFoundException {
        int aWrongID = Integer.parseInt(A_WRONG_URL_ID);
        doReturn(false).when(userDirectory).userExists(aWrongID);

        firefighterTreatment.processRequest(A_WRONG_URL_ID, A_ZONE);
    }
}
