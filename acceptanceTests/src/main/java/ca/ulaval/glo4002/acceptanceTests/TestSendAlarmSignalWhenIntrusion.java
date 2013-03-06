package ca.ulaval.glo4002.acceptanceTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestSendAlarmSignalWhenIntrusion {

    private static TestFixture fixture;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();
    }

    @Before
    public void setUp() {
        fixture.createAlarmSystem();
        fixture.initializeAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void teardown() {
        fixture.setReceivedCallToFalse();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        fixture.stopServers();
    }

    // This test takes at least 30 seconds. Don't run it if you're in a hurry
    @Test
    public void emergenciesCalledThirtySecondsAfterMainDoorIntrusion() throws InterruptedException {
        fixture.openMainDoor();

        fixture.verifyPoliceWasNotCalled();
        fixture.verifyPoliceWasCalledAfterThirtySeconds();
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