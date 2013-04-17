package ca.ulaval.glo4002.acceptanceTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestAlarmLog {

    private static TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();

        fixture.createAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void tearDownClass() throws Exception {
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