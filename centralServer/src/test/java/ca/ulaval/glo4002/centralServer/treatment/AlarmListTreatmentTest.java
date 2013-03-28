package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class AlarmListTreatmentTest {

    private static final String A_GOOD_URL_ID = "20";
    private static final String A_WRONG_URL_ID = "13";

    @Mock
    private User user;

    @Mock
    private UserDirectory userDirectory;

    @InjectMocks
    private AlarmListTreatment alarmListTreatment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenProcessingTheRequestWithAGoodUserIDThenTheLogFromTheUserIsRetrieved() throws UserNotFoundException {
        int aGoodID = Integer.parseInt(A_GOOD_URL_ID);
        doReturn(true).when(userDirectory).userExists(aGoodID);
        doReturn(user).when(userDirectory).obtainUser(aGoodID);

        alarmListTreatment.retrieveLogFromUser(A_GOOD_URL_ID);

        verify(user).createLogForAllAlarms();
    }

    @Test(expected = UserNotFoundException.class)
    public void whenProcessingTheRequestWithAWrongUserIDThenANotFoundUserExceptionIsThrown() throws UserNotFoundException {
        int aWrongID = Integer.parseInt(A_WRONG_URL_ID);
        doReturn(false).when(userDirectory).userExists(aWrongID);

        alarmListTreatment.retrieveLogFromUser(A_WRONG_URL_ID);
    }

}
