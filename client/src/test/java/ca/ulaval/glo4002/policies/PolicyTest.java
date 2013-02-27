package ca.ulaval.glo4002.policies;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class PolicyTest {

    private final int ZONE_OF_THE_DETECTOR = 5;
    private Policy policy;

    @Mock
    private AlarmSystem alarmSystem;
    @Mock
    private CommunicationUnit communicationUnit;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policy = createPolicy();
    }

    @Test
    public void whenSystemIsNotArmedThereIsNoMessageSent() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.execute(ZONE_OF_THE_DETECTOR);
        verify(communicationUnit, never()).send();
    }

    @Test
    public void whenSystemIsArmedThereIsAMessageSent() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.execute(ZONE_OF_THE_DETECTOR);
        verify(communicationUnit).send();
    }

    private Policy createPolicy() {
        Policy abstractPolicy = new Policy(alarmSystem, communicationUnit) {

        };
        return abstractPolicy;
    }

}
