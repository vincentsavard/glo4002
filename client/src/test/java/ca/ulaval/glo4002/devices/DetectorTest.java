package ca.ulaval.glo4002.devices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.policies.Policy;

public class DetectorTest {

    private static int A_ZONE = 10;

    private Detector detector;

    @Mock
    private Policy policy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        detector = new Detector(policy, A_ZONE);
    }

    @Test
    public void whenDetectorIsTriggeredThenThePolicyIsExcuted() {
        detector.trigger();
        Mockito.verify(policy).executeProcedure(A_ZONE);
    }

}
