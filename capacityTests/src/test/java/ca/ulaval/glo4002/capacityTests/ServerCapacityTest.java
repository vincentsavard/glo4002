package ca.ulaval.glo4002.capacityTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.domain.user.UserDirectoryLocator;
import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.core.communication.Communicator.TargetResource;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

public class ServerCapacityTest {

    private static final String AN_ADDRESS = "rue des tests";
    private final long MILLISECONDS_PER_DAY = 86400000;
    private final long RUNNING_TIME_IN_DAYS = 14;

    private CentralServer centralServer;
    private Communicator communicator;
    private EmergencyServer emergencyServer;

    @Before
    public void setUp() throws Exception {
        centralServer = new CentralServer();
        emergencyServer = new EmergencyServer();
        centralServer.startServer();
        emergencyServer.startServer();
        communicator = new Communicator(AN_ADDRESS);
    }

    @After
    public void tearDown() throws Exception {
        centralServer.stopServer();
        emergencyServer.stopServer();
        UserDirectoryLocator.getInstance().deleteDirectory();
    }

    @Test
    public void serverCanHandleContinuousRequestsOverAGivenPeriodOfTime() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + RUNNING_TIME_IN_DAYS * MILLISECONDS_PER_DAY;
        while (System.currentTimeMillis() < endTime) {
            communicator.sendMessageToCentralServer(TargetResource.POLICE);
        }
    }

}
