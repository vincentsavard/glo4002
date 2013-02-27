package ca.ulaval.glo4002.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SoftwareFireAlarmAdapterTest {

	private FireAlarmAdapter alarmAdapter;
	
	@Before
	public void initializeAlarmAdapter(){
		alarmAdapter = new SoftwareFireAlarmAdapter();
	}
	
	@Test
	public void isAlarmActivatedWhenTrigered() {
		alarmAdapter.activateAlarm();
		
		assertTrue(alarmAdapter.isAlarmActivated());
	}
}
