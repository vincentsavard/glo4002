package ca.ulaval.glo4002.testAcceptance;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.devices.InvalidPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestDisarmViaKeypad {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();

        fixture.createAlarmSystem();
        fixture.armSystem();
    }

    @Test
    public void systemIsDisarmedWhenDisarmedWithGoodNIP() {
        fixture.disarmSystemWithGoodNIP();

        fixture.assertAlarmSystemIsNotArmed();
    }

    @Test
    public void systemIsArmedWhenDisarmedWithWrongNIP() {
        try {
            fixture.disarmSystemWithWrongPIN();
            fail("InvalidPINException expected.");
        } catch (InvalidPINException e) {
            fixture.assertAlarmSystemIsArmed();
        }
    }

}