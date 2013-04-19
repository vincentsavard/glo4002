package ca.ulaval.glo4002.acceptanceTests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestAlarmLog {

    private static TestFixture fixture;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fixture = new TestFixture();
    }

    @Before
    public void setUp() throws Exception {
        fixture.initServers();
        fixture.createAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void teardown() throws Exception {
        fixture.setReceivedCallToFalse();
        fixture.stopServers();
    }

    @Test
    public void emptyAlarmLog() throws Exception {
        fixture.verifyAlarmLogIsEmpty();
    }

    @Test
    public void notEmptyAlarmLog() throws Exception {
        fixture.openSecondaryDoor();
        fixture.verifyAlarmLogIsNotEmpty();
    }

}