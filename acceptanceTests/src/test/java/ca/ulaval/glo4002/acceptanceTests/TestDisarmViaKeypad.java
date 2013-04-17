package ca.ulaval.glo4002.acceptanceTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.devices.InvalidPINException;
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
    public void teardown() {
        fixture.setReceivedCallToFalse();
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        fixture.stopServers();
    }

    @Test
    public void systemIsDisarmedWhenDisarmedWithGoodNIP() {
        fixture.disarmSystemWithGoodNIP();
        fixture.verifyAlarmSystemIsNotArmed();
    }

    @Test
    public void systemIsArmedWhenDisarmedWithWrongNIP() {
        try {
            fixture.disarmSystemWithWrongPIN();
            fail("InvalidPINException expected.");
        } catch (InvalidPINException e) {
            fixture.verifyAlarmSystemIsArmed();
        }
    }

}