package ca.ulaval.glo4002.context;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;

public class HouseContextTest {

    private static final int A_ZONE = 1;
    private static final int CONTEXT_HAS_ONE_DETECTOR = 1;
    private static final int CONTEXT_HAS_THREE_DETECTORS = 3;

    private HouseContext houseContext;

    @Mock
    private AlarmSystem alarmSystem;

    @Mock
    private Communicator communicator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        houseContext = new HouseContext(alarmSystem, communicator);
    }

    @Test
    public void whenCreatingAMainDoorDetectorTheHouseContextContainsOneDetector() {
        houseContext.createMainDoorDetector(A_ZONE);
        assertEquals(houseContext.getNumberOfDetectors(), CONTEXT_HAS_ONE_DETECTOR);
    }

    @Test
    public void whenCreatingAnIntrusionDetectorTheHouseContextContainsOneDetector() {
        houseContext.createIntrusionDetector(A_ZONE);
        assertEquals(houseContext.getNumberOfDetectors(), CONTEXT_HAS_ONE_DETECTOR);
    }

    @Test
    public void whenCreatingAFireDetectorTheHouseContextContainsOneDetector() {
        houseContext.createFireDetector(A_ZONE);
        assertEquals(houseContext.getNumberOfDetectors(), CONTEXT_HAS_ONE_DETECTOR);
    }

    @Test
    public void whenCreatingThreeDifferentDetectorsTheHouseContextContainsThreeDetectors() {
        houseContext.createMainDoorDetector(A_ZONE);
        houseContext.createIntrusionDetector(A_ZONE);
        houseContext.createFireDetector(A_ZONE);

        assertEquals(houseContext.getNumberOfDetectors(), CONTEXT_HAS_THREE_DETECTORS);
    }

}
