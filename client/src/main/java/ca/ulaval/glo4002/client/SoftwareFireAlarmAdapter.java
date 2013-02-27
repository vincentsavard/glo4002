package ca.ulaval.glo4002.client;

public class SoftwareFireAlarmAdapter extends FireAlarmAdapter{

	public SoftwareFireAlarmAdapter() {
		alarmActivated = false;
	}
	
    public void activateAlarm() {
    	alarmActivated = true;
    }

    public void deactivateAlarm() {
    	alarmActivated = false;
    }

    public boolean isAlarmActivated() {
	    return alarmActivated;
    }
}
