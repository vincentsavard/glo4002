package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public class PoliceTreatmentTest {

    private static final String A_GOOD_ID = "20";

    private PoliceTreatment policeTreatment;

    @Mock
    private CommunicationUnit communicationUnit;

    @Mock
    private UsersDirectory usersDirectory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usersDirectory = mock(UsersDirectory.class);
        policeTreatment = new PoliceTreatment(usersDirectory, communicationUnit);
    }

    @Test
    public void whenProcessingTheRequestWithAGoodUserIdThenCommunicationUnitSendSomething() throws UserNotFoundException {
        when(usersDirectory.userExists(anyInt())).thenReturn(true);
        policeTreatment.processRequest(A_GOOD_ID);
        verify(communicationUnit).sendMessageToEmergencyServer(any(User.class));
    }

    @Test(expected = UserNotFoundException.class)
    public void whenProcessingTheRequestWithAWrongUserIdThenANotFoundUserExceptionIsThrown() throws UserNotFoundException {
        when(usersDirectory.userExists(Integer.parseInt(A_GOOD_ID))).thenReturn(false);
        policeTreatment.processRequest(A_GOOD_ID);
    }
}
