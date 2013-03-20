package ca.ulaval.glo4002.policies;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class PolicyTest {

    private Policy policy;

    @Mock
    private AlarmSystem alarmSystem;

    @Mock
    private Communicator communicator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policy = createPolicy();
    }

    @Test
    public void whenSystemIsNotArmedThereIsNoMessageSent() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.executeProcedure();
        verify(communicator, never()).sendMessageToCentralServer();
    }

    @Test
    public void whenSystemIsArmedThereIsAMessageSent() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.executeProcedure();
        verify(communicator).sendMessageToCentralServer();
    }

    private Policy createPolicy() {
        Policy abstractPolicy = new Policy(alarmSystem, communicator) {

        };
        return abstractPolicy;
    }

}
