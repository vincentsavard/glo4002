package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.user.Alarm;
import ca.ulaval.glo4002.centralServer.user.Alarm.AlarmType;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;

public class EmergencyTreatmentTest {

    private static final String A_GOOD_URL_ID = "20";

    @Mock
    private User user;

    @Mock
    private Communicator communicator;

    @Mock
    private UserDirectory userDirectory;

    private EmergencyTreatment emergencyTreatment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emergencyTreatment = createEmergencyTreatment();
    }

    @Test
    public void whenLoggingAlarmThenAlarmIsAddedToTheRightUserList() {
        int aGoodID = Integer.parseInt(A_GOOD_URL_ID);
        doReturn(user).when(userDirectory).obtainUser(aGoodID);

        emergencyTreatment.addAlarmToUserList(aGoodID, any(AlarmType.class));

        verify(user).addAlarm(any(Alarm.class));
    }

    private EmergencyTreatment createEmergencyTreatment() {
        EmergencyTreatment abstractEmergencyTreatment = new EmergencyTreatment(communicator, userDirectory) {

        };
        return abstractEmergencyTreatment;
    }

}
