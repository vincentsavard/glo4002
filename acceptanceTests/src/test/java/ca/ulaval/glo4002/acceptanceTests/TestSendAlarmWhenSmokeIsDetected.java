package ca.ulaval.glo4002.acceptanceTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestSendAlarmWhenSmokeIsDetected {

    private static TestFixture fixture;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();
    }

    @Before
    public void setUp() {
        fixture.createAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void tearDown() {
        fixture.setReceivedCallToFalse();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        fixture.stopServers();
    }

    @Test
    public void emergenciesAreCalledWhenSmokeIsDetected() {
        fixture.detectSmoke();

        fixture.verifySirenIsOn();
        fixture.firemenWereCalled();
        fixture.verifyZoneWasTransmittedToCentral();
    }

}