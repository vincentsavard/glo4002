package ca.ulaval.glo4002.acceptanceTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.devices.InvalidPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestArmViaKeypad {

    private TestFixture fixture;

    @Before
    public void setUp() {
        fixture = new TestFixture();
        fixture.createAlarmSystem();
    }

    @Test
    public void systemIsArmedWithFastPIN() {
        fixture.armSystemWithFastPIN();
        fixture.verifyAlarmSystemIsArmed();
    }

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
        fixture.initServers();

        fixture.initializeAlarmSystem();

        fixture.armSystemWithDefaultPIN();

        // fixture.openSecondaryDoor();
        fixture.verifyAlarmSystemWaitsThirtySecondsBeforeArming();

        fixture.verifyPoliceWasNotCalled();
        fixture.stopServers();
    }

}