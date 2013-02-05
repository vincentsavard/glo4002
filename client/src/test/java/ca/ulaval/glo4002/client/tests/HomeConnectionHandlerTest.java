package ca.ulaval.glo4002.client.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.client.HomeConnectionHandler;

public class HomeConnectionHandlerTest {

	HomeConnectionHandler homeConnectionHandler;

	@Before
	public void initHomeConnectionHandler() {
		homeConnectionHandler = new HomeConnectionHandler();
	}

	@Test
	public void homeHasBeenInitializedCorrectly() {
		assertTrue(homeConnectionHandler.getWebResource() != null);
	}
}
