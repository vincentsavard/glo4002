package ca.ulaval.glo4002.policies;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.utilities.DelayTimer;

public class MainDoorIntrusionPolicyTest {

    private static final int A_ZONE = 1;

    private MainDoorIntrusionPolicy policy;

    @Mock
    private AlarmSystem alarmSystem;

    @Mock
    private DelayTimer delayTimer;

    @Mock
    private Communicator communicator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policy = new MainDoorIntrusionPolicy(alarmSystem, communicator, delayTimer);
    }

    @Test
    public void whenSystemIsNotArmedDelayTimerDoesNotStart() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.executeProcedure(A_ZONE);
        verify(delayTimer, never()).startDelay(anyInt());
    }

    @Test
    public void whenSystemIsArmedDelayTimerStarts() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.executeProcedure(A_ZONE);
        verify(delayTimer).startDelay(anyInt());
    }

    @Test
    public void whenDelayIsExpiredAndSystemIsNotArmedCommunicatorDoesNotMessage() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.delayExpired();
        verify(communicator, never()).sendMessageToCentralServer(any(Communicator.TargetResource.class));
    }

    @Test
    public void whenDelayIsExpiredAndSystemIsArmedCommunicatorSendsMessage() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.delayExpired();
        verify(communicator).sendMessageToCentralServer(any(Communicator.TargetResource.class));
    }

}