package ca.ulaval.glo4002.endToEndTesting;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

public class EndToEndTest {

	@BeforeClass
	public static void setUp() throws Exception {
		CentralServer.startServer();
		EmergencyServer.startServer();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		CentralServer.stopServer();
		EmergencyServer.stopServer();
	}

	@Test
	@Ignore
	public void canSendRequestFromClientToCentralServer() {
		// Home home = new Home();
		// home.armSystem();
		// home.openMainDoor();

		// TODO Complete this test

		// RequestTreatment emergencyTreatmentNodeMock =
		// mock(RequestTreatment.class);
		// verify(emergencyTreatmentNodeMock).treatRequest("/");
	}
}
