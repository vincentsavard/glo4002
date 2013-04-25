package ca.ulaval.glo4002.centralServer.domain.treatment;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.core.communication.Communicator;
import ca.ulaval.glo4002.centralServer.core.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.domain.user.Alarm;
import ca.ulaval.glo4002.centralServer.domain.user.User;
import ca.ulaval.glo4002.centralServer.domain.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.domain.user.UserNotFoundException;

public class EmergencyTreatmentTest {

    private static final int A_GOOD_URL_ID = 20;
    private static final int A_WRONG_URL_ID = 10;
    private static final String A_MESSAGE = "message";
    private static final CommunicationType A_COMMUNICATION_TYPE = CommunicationType.POLICE;

    @Mock
    private User user;

    @Mock
    private UserDirectory userDirectory;

    @Mock
    private Communicator communicator;

    private EmergencyTreatment emergencyTreatment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emergencyTreatment = new EmergencyTreatment(A_COMMUNICATION_TYPE, communicator, userDirectory);
    }

    @Test
    public void whenLoggingAlarmThenAlarmIsAddedToTheRightUserList() {
        doReturn(user).when(userDirectory).obtainUser(A_GOOD_URL_ID);

        emergencyTreatment.addAlarmToUserList(A_GOOD_URL_ID);

        verify(user).addAlarm(any(Alarm.class));
    }

    @Test
    public void whenProcessingTheRequestWithAGoodUserIDThenCommunicatorSendsNotificationToEmergencyServer() {
        setGoodIDInUserDirectory(A_GOOD_URL_ID);
        emergencyTreatment.processRequest(A_GOOD_URL_ID);
        verify(communicator).sendMessageToEmergencyServer(A_COMMUNICATION_TYPE, user);
    }

    @Test
    public void whenProcessingTheRequestWithAGoodUserIDAndAMessageThenCommunicatorSendsNotificationWithTheMessageToEmergencyServer() {
        setGoodIDInUserDirectory(A_GOOD_URL_ID);
        emergencyTreatment.processRequest(A_GOOD_URL_ID, A_MESSAGE);
        verify(communicator).sendMessageToEmergencyServer(A_COMMUNICATION_TYPE, user, A_MESSAGE);
    }

    private void setGoodIDInUserDirectory(int goodID) {
        doReturn(true).when(userDirectory).userExists(goodID);
        doReturn(user).when(userDirectory).obtainUser(goodID);
    }

    @Test(expected = UserNotFoundException.class)
    public void whenProcessingTheRequestWithAWrongUserIDThenANotFoundUserExceptionIsThrown() {
        setWrongIDInUserDirectory(A_WRONG_URL_ID);
        emergencyTreatment.processRequest(A_WRONG_URL_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void whenProcessingTheRequestWithAWrongUserIDAndAMessageThenANotFoundUserExceptionIsThrown() {
        setWrongIDInUserDirectory(A_WRONG_URL_ID);
        emergencyTreatment.processRequest(A_WRONG_URL_ID, A_MESSAGE);
    }

    private void setWrongIDInUserDirectory(int wrongID) {
        doReturn(false).when(userDirectory).userExists(wrongID);
    }

}
