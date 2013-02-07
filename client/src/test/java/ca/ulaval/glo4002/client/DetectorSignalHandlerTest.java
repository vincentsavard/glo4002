package ca.ulaval.glo4002.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.client.CommunicationUnit;
import ca.ulaval.glo4002.client.SignalHandler;

public class DetectorSignalHandlerTest {

	private SignalHandler detectorSignalHandler;
	private final String ANY_STRING = "a very common string";

	@Before
	public void initClient() {
		detectorSignalHandler = new SignalHandler();
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
		SignalHandler detectorSignalHandlerMocked = new SignalHandler() {
			@Override
			protected void initHomeConnectionHandler() {
				communicationUnit = communicationUnitMocked;
			}
		};

		detectorSignalHandlerMocked.sendRequestToCentralServer(ANY_STRING);
		verify(communicationUnitMocked).sendPostRequest(ANY_STRING);
	}

}
