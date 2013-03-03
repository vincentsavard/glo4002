package ca.ulaval.glo4002.policies;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.utilities.DelayTimer;

public class MainDoorIntrusionPolicyTest {

    private MainDoorIntrusionPolicy policy;

    @Mock
    private AlarmSystem alarmSystem;

    @Mock
    private DelayTimer delayTimer;

    @Mock
    private CommunicationUnit communicationUnit;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policy = new MainDoorIntrusionPolicy(alarmSystem, communicationUnit, delayTimer);
    }

    @Test
    public void whenSystemIsNotArmedDelayTimerDoesNotStart() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.executeProcedure();
        verify(delayTimer, never()).startDelay(anyInt());
    }

    @Test
    public void whenSystemIsArmedDelayTimerStarts() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.executeProcedure();
        verify(delayTimer).startDelay(anyInt());
    }

    @Test
    public void whenDelayIsExpiredAndSystemIsNotArmedCommunicationUnitDoesNotMessage() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.delayExpired();
        verify(communicationUnit, never()).send();
    }

    @Test
    public void whenDelayIsExpiredAndSystemIsArmedCommunicationUnitSendsMessage() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.delayExpired();
        verify(communicationUnit).send();
    }
}