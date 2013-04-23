package ca.ulaval.glo4002.acceptanceTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.domain.devices.InvalidPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestDisarmViaKeypad {

    private static TestFixture fixture;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();
    }

    @Before
    public void setUp() throws Exception {
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
    public void systemIsDisarmedWhenDisarmedWithGoodPIN() {
        fixture.disarmSystemWithGoodPIN();
        fixture.verifyAlarmSystemIsNotArmed();
    }

    @Test
    public void systemIsArmedWhenDisarmedWithWrongPIN() {
        try {
            fixture.disarmSystemWithWrongPIN();
            fail("InvalidPINException expected.");
        } catch (InvalidPINException e) {
            fixture.verifyAlarmSystemIsArmed();
        }
    }

}