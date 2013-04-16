package ca.ulaval.glo4002.policies;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.policies.PolicyFactory.PolicyType;

public class PolicyFactoryTest {

    private PolicyType INVALID_POLICY;
    private PolicyFactory policyFactory;

    @Mock
    private AlarmSystem alarmSystem;

    @Mock
    private Communicator communicator;

    @Mock
    private Policy invalidPolicy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policyFactory = new PolicyFactory(alarmSystem, communicator);
    }

    @Test
    public void whenCreatingFirePolicyAFirePolicyIsReturned() {
        Policy firePolicy = policyFactory.createPolicy(PolicyType.FIRE_POLICY);
        assertTrue(firePolicy instanceof FirePolicy);
    }

    @Test
    public void whenCreatingIntrusionPolicyAIntrusionPolicyIsReturned() {
        Policy intrusionPolicy = policyFactory.createPolicy(PolicyType.INTRUSION_POLICY);
        assertTrue(intrusionPolicy instanceof IntrusionPolicy);
    }

    @Test
    public void whenCreatingMainDoorPolicyAMainDoorPolicyIsReturned() {
        Policy mainDoorPolicy = policyFactory.createPolicy(PolicyType.MAIN_DOOR_INTRUSION_POLICY);
        assertTrue(mainDoorPolicy instanceof MainDoorIntrusionPolicy);
    }

    @Test(expected = InvalidPolicyException.class)
    public void whenCreatingInvalidPolicyThenThrowAnException() {
        invalidPolicy = policyFactory.createPolicy(INVALID_POLICY);
    }

}
