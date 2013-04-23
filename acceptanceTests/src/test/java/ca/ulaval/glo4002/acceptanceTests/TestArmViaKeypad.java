package ca.ulaval.glo4002.acceptanceTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.domain.devices.InvalidPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestArmViaKeypad {

    private static TestFixture fixture;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();
    }

    @Before
    public void setUp() throws Exception {
        fixture.createAlarmSystem();
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
    public void systemIsArmedWithFastPIN() {
        fixture.armSystemWithFastPIN();
        fixture.verifyAlarmSystemIsArmed();
    }

    @Test
    public void systemIsArmedWithGoodPIN() {
        fixture.armSystemWithDefaultPIN();
        fixture.verifyAlarmSystemIsArmed();
    }

    @Test
    public void systemIsNotArmedWithWrongPIN() {
        try {
            fixture.armSystemWithWrongPIN();
            fail("InvalidPINException expected.");
        } catch (InvalidPINException e) {
            fixture.verifyAlarmSystemIsNotArmed();
        }
    }

    // This test takes at least 30 seconds. Don't run it if you're in a hurry
    @Test
    public void alarmSystemWaitsThirtySecondsBeforeArmingViaKeypad() throws Exception {
        fixture.armSystemWithDefaultPIN();

        fixture.openSecondaryDoor();
        fixture.verifyAlarmSystemWaitsThirtySecondsBeforeArming();

        fixture.verifyPoliceWasNotCalled();
    }

}