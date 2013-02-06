package ca.ulaval.glo4002.client.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.client.CommunicationUnit;
import ca.ulaval.glo4002.client.DetectorSignalHandler;

public class DetectorSignalHandlerTest {

	private DetectorSignalHandler detectorSignalHandler;
	private final String ANY_STRING = "a very common string";

	@Before
	public void initClient() {
		detectorSignalHandler = new DetectorSignalHandler();
	}

	@Test
	public void communicationUnitWasInitializedCorrectly() {
		assertTrue(detectorSignalHandler.getCommunicationUnit() != null);
	}

	@Test
	public void SystemStateWasInitializedCorrectly() {
		assertTrue(detectorSignalHandler.getSystemState() != null);
	}

	@Test
	public void systemSendsRequestToCentralServer() {
		final CommunicationUnit communicationUnitMocked = mock(CommunicationUnit.class);
		DetectorSignalHandler detectorSignalHandlerMocked = new DetectorSignalHandler() {
			@Override
			protected void initHomeConnectionHandler() {
				communicationUnit = communicationUnitMocked;
			}
		};

		detectorSignalHandlerMocked.sendRequestToCentralServer(ANY_STRING);
		verify(communicationUnitMocked).sendPostRequest(ANY_STRING);
	}

}
