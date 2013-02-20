package ca.ulaval.glo4002.endToEndTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.client.DetectorAdapter;
import ca.ulaval.glo4002.client.Signal;
import ca.ulaval.glo4002.client.SignalHandler;
import ca.ulaval.glo4002.client.SoftwareDetectorAdapter;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

public class EndToEndTest {
    private String RESPONSE_TO_POST_REQUEST_FROM_EMERGENCY = "POST request received at emergency server";
    private String RESPONSE_TO_POST_REQUEST_FROM_CENTRAL = "POST request received at central server";
    private String RESPONSE_TO_POST_REQUEST = RESPONSE_TO_POST_REQUEST_FROM_CENTRAL
            + RESPONSE_TO_POST_REQUEST_FROM_EMERGENCY;
    private int TIME_TO_DISARM_THE_ALARM_WHEN_SECONDARY_DOOR_OPENS = 0;

    private CentralServer centralServer;
    private EmergencyServer emergencyServer;
    private SignalHandler signalHandler;
    private DetectorAdapter softwareDetector;

    @Before
    public void setUp() throws Exception {
        centralServer = new CentralServer();
        emergencyServer = new EmergencyServer();

        centralServer.startServer();
        emergencyServer.startServer();

        signalHandler = SignalHandler.getInstance();
        softwareDetector = new SoftwareDetectorAdapter();
    }

    @After
    public void tearDown() throws Exception {
        centralServer.stopServer();
        emergencyServer.stopServer();
    }

    @Test
    public void endToEndTest() {
        softwareDetector.sendSignalToSignalHandler(
                Signal.DetectorType.SECONDARY_DOOR,
                TIME_TO_DISARM_THE_ALARM_WHEN_SECONDARY_DOOR_OPENS);
        String lastResponseFromServer = signalHandler.getLastResponseReceived();

        assertEquals(RESPONSE_TO_POST_REQUEST, lastResponseFromServer);
    }
}
