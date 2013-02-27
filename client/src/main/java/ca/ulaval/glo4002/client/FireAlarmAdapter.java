package ca.ulaval.glo4002.client;

public abstract class FireAlarmAdapter {
	
	protected boolean alarmActivated;

	public abstract void activateAlarm();

	public abstract void deactivateAlarm();

	public abstract boolean isAlarmActivated();
}
