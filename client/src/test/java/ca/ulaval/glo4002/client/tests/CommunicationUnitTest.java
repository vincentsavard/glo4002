package ca.ulaval.glo4002.client.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.client.CommunicationUnit;

public class CommunicationUnitTest {

	CommunicationUnit homeConnectionHandler;

	@Before
	public void initHomeConnectionHandler() {
		homeConnectionHandler = new CommunicationUnit();
	}

	@Test
	public void homeHasBeenInitializedCorrectly() {
		assertTrue(homeConnectionHandler.getWebResource() != null);
	}
}
