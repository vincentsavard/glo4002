package ca.ulaval.glo4002.acceptanceTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.devices.InvalidPINException;
import ca.ulaval.glo4002.devices.RecentlyUsedPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestChangePINViaKeypad {

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
    public void PINIsChangedWhenValidPINIsGiven() {
        fixture.requestPINChangeWithDefaultPIN();
        fixture.verifyDefaultPINHasBeenChangedForNewPIN();
    }

    @Test(expected = InvalidPINException.class)
    public void ExceptionIsThrownWhenChangeRequestIsMadeWithInvalidPIN() {
        fixture.requestPINChangeWithWrongPIN();
    }

    @Test(expected = RecentlyUsedPINException.class)
    public void ExceptionIsThrownWhenNewPINIsEqualToCurrentPIN() {
        fixture.requestPINChangeWithCurrentPIN();
    }

    @Test(expected = RecentlyUsedPINException.class)
    public void ExceptionIsThrownWhenNewPINIsEqualToRecentlyUsedPIN() {
        fixture.requestPINChangeWithPreviousPIN();
    }

    @Test(expected = InvalidPINException.class)
    public void PINIsNotChangedWhenInvalidPINIsGiven() {
        fixture.requestPINChangeWithWrongPIN();
        fixture.verifyDefaultPINIsStillTheValidPIN();
    }

}