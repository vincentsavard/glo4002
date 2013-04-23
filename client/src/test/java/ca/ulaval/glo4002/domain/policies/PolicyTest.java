package ca.ulaval.glo4002.domain.policies;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;
import ca.ulaval.glo4002.domain.policies.Policy;

public class PolicyTest {

    private static int A_ZONE = 10;

    private Policy policy;

    @Mock
    private AlarmSystem alarmSystem;

    @Mock
    private Communicator communicator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policy = new Policy(alarmSystem, communicator) {

        };
    }

    @Test
    public void whenSystemIsNotArmedThereIsNoMessageSent() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.executeProcedure(A_ZONE);
        verify(communicator, never()).sendMessageToCentralServer(any(Communicator.TargetResource.class));
    }

    @Test
    public void whenSystemIsArmedThereIsAMessageSent() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.executeProcedure(A_ZONE);
        verify(communicator).sendMessageToCentralServer(any(Communicator.TargetResource.class));
    }

}
