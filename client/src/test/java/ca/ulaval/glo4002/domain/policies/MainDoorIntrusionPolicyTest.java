package ca.ulaval.glo4002.domain.policies;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.core.timer.DelayTimer;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;
import ca.ulaval.glo4002.domain.policies.MainDoorIntrusionPolicy;

public class MainDoorIntrusionPolicyTest {

    private static final int A_ZONE = 1;

    @InjectMocks
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
    public void whenDelayIsExpiredAndSystemIsNotArmedThenCommunicatorDoesNotMessage() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.delayExpired();
        verify(communicator, never()).sendMessageToCentralServer(any(Communicator.TargetResource.class));
    }

    @Test
    public void whenDelayIsExpiredAndSystemIsArmedThenCommunicatorSendsMessage() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.delayExpired();
        verify(communicator).sendMessageToCentralServer(any(Communicator.TargetResource.class));
    }

}