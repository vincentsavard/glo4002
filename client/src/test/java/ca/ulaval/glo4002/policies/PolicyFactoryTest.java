package ca.ulaval.glo4002.policies;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.policies.PolicyFactory.PolicyType;

public class PolicyFactoryTest {

    private PolicyFactory policyFactory;

    @Mock
    private AlarmSystem alarmSystem;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policyFactory = new PolicyFactory(alarmSystem);
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

}
