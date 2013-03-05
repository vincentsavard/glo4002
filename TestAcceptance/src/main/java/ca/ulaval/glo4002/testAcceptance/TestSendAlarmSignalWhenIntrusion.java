package ca.ulaval.glo4002.testAcceptance;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestSendAlarmSignalWhenIntrusion {

    private static final int THIRTY_SECONDS_IN_MILLISECONDS = 30000;
    private static TestFixture fixture;
    private CentralServer centralServer;

    private final int FIFTEEN_SECONDS = 15000;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();
    }

    @Before
    public void setUp() throws Exception {
        // fixture.waitSeconds(FIFTEEN_SECONDS);
        fixture.createAlarmSystem();
        fixture.initializeAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void teardown() throws Exception {
        fixture.setReceivedCallToFalse();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        fixture.stopServers();
    }

    @Test
    public void emergenciesCalledThirtySecondsAfterMainDoorIntrusion() throws InterruptedException {
        fixture.openMainDoor();

        fixture.verifyPoliceWasNotCalled();

        fixture.verifyPoliceWasCalledAfterMilliSeconds(THIRTY_SECONDS_IN_MILLISECONDS);

        fixture.verifyPoliceWasCalled();
    }

    @Test
    public void emergenciesCalledWhenSecondaryDoorIntrusion() {
        fixture.openSecondaryDoor();

        fixture.verifyPoliceWasCalled();
    }

    @Test
    public void emergenciesCalledWhenMovementDetected() {
        fixture.triggerMovementDetector();

        fixture.verifyPoliceWasCalled();
    }
}