package ca.ulaval.glo4002.client.tests;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.client.Home;
import ca.ulaval.glo4002.client.HomeConnectionHandler;

public class HomeTest {

	private Home home;

	@Before
	public void initClient() {
		home = new Home();
	}

	@Test
	public void homeWasInitializedCorrectly() {
		assertTrue(home.getHomeConnectionHandler() != null);
	}

	@Test
	public void initialAlarmStatusIsUnarmed() {
		assertFalse(home.systemIsArmed());
	}

	@Test
	public void systemCanBeArmed() {
		home.armSystem();
		assertTrue(home.systemIsArmed());
	}

	@Test
	public void systemCanDetectMainDoorOpening() {
		home.openMainDoor();
		assertTrue(home.mainDoorIsOpen());
	}

	@Test
	public void systemSendsRequestToServerWhenAnIntrusionIsDetected() {
		final HomeConnectionHandler homeConnectionHandlerMocked = mock(HomeConnectionHandler.class);
		Home mockedHome = new Home() {
			@Override
			protected void setHomeConnectionHandler() {
				homeConnectionHandler = homeConnectionHandlerMocked;
			}
		};

		mockedHome.armSystem();
		mockedHome.openMainDoor();

		verify(homeConnectionHandlerMocked).sendPostRequest(anyString());
	}

}
