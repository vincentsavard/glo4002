package ca.ulaval.glo4002.endToEndTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.client.Home;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServerMain;

public class EndToEndTest {

	@Before
	public void setUp() throws Exception {
		CentralServer.startServer();
		EmergencyServerMain.startServer();
	}

	@After
	public void tearDown() throws Exception {
		CentralServer.stopServer();
		EmergencyServerMain.stopServer();
	}

	@Test
	public void canSendRequestFromClientToCentralServer() {
		Home home = new Home();
		home.armSystem();
		home.openMainDoor();

		// TODO Complete this test

		// RequestTreatment emergencyTreatmentNodeMock =
		// mock(RequestTreatment.class);
		// verify(emergencyTreatmentNodeMock).treatRequest("/");
	}
}
