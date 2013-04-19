package ca.ulaval.glo4002.systemContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.systemContext.SystemContext;

public class SystemContextTest {
    
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
        systemContext.createMainDoorDetector();
        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_ONE_DETECTOR);
    }
    
    @Test
    public void whenCreatingAnIntrusionDetectorTheSystemContextContainsOneDetector() {
        systemContext.createIntrusionDetector();
        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_ONE_DETECTOR);
    }
    
    @Test
    public void whenCreatingAFireDetectorTheSystemContextContainsOneDetector() {
        systemContext.createFireDetector();
        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_ONE_DETECTOR);
    }
    
    @Test
    public void whenCreatingThreeDifferentDetectorTheSystemContextContainsThreeDetectors() {
        systemContext.createMainDoorDetector();
        systemContext.createIntrusionDetector();
        systemContext.createFireDetector();
        
        assertEquals(systemContext.getNumberOfDetectors(), CONTEXT_HAVE_THREE_DETECTORS);
    }

}
