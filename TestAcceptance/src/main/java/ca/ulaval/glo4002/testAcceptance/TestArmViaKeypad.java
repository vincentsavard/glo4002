package ca.ulaval.glo4002.testAcceptance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.devices.InvalidPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestArmViaKeypad {

    private static final int THIRTY_SECONDS = 30000;
    private static final int FIFTEEN_SECONDS = 15000;
    private static final int TEN_SECOND = 10000;

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.createAlarmSystem();
    }

    @After
    public void teardown() throws Exception {}

    @Test
    public void systemIsArmedWithFastPIN() {
        fixture.armSystemWithFastPIN();
        fixture.assertAlarmSystemIsArmed();
    }

    public void systemIsArmedWithGoodPIN() {
        fixture.armSystemWithDefaultPIN();

        fixture.assertAlarmSystemIsArmed();
    }

    @Test
    public void systemIsNotArmedWithWrongPIN() {
        try {
            fixture.armSystemWithWrongPIN();
            fail("InvalidPINException expected.");
        } catch (InvalidPINException e) {
            fixture.assertAlarmSystemIsNotArmed();
        }
    }

    @Test
    public void alarmSystemWaitsThirtySecondsBeforeArmingViaKeypad() throws Exception {
        fixture.initServers();

        fixture.initializeAlarmSystem();

        fixture.armSystemWithDefaultPIN();

        fixture.openSecondaryDoor();
        fixture.verifyPoliceWasCalledAfterMilliSeconds(THIRTY_SECONDS);

        fixture.verifyPoliceWasNotCalled();
        fixture.stopServers();
    }
}