package ca.ulaval.glo4002.systemContext;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class SystemContextTest {

    private static final int A_ZONE = 1;
    private static final int CONTEXT_HAVE_ONE_DETECTOR = 1;
    private static final int CONTEXT_HAVE_THREE_DETECTORS = 3;

    private SystemContext systemContext;

    @Mock
    private AlarmSystem alarmSystem;

    @Mock
    private Communicator communicator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        systemContext = new SystemContext(alarmSystem, communicator);
    }

    @Test
    public void whenCreatingAMainDoorDetectorTheSystemContextContainsOneDetector() {
        systemContext.createMainDoorDetector(A_ZONE);
        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_ONE_DETECTOR);
    }

    @Test
    public void whenCreatingAnIntrusionDetectorTheSystemContextContainsOneDetector() {
        systemContext.createIntrusionDetector(A_ZONE);
        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_ONE_DETECTOR);
    }

    @Test
    public void whenCreatingAFireDetectorTheSystemContextContainsOneDetector() {
        systemContext.createFireDetector(A_ZONE);
        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_ONE_DETECTOR);
    }

    @Test
    public void whenCreatingThreeDifferentDetectorTheSystemContextContainsThreeDetectors() {
        systemContext.createMainDoorDetector(A_ZONE);
        systemContext.createIntrusionDetector(A_ZONE);
        systemContext.createFireDetector(A_ZONE);

        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_THREE_DETECTORS);
    }

}
