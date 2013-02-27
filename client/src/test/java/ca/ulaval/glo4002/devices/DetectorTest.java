package ca.ulaval.glo4002.devices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.policies.Policy;

public class DetectorTest {

    private Detector detector;

    @Mock
    private Policy policy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        detector = new Detector(policy);
    }

    @Test
    public void whenDetectorIsTriggeredThePolicyIsExcuted() {
        detector.trigger();
        Mockito.verify(policy).execute();
    }
}
