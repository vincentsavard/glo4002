package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class AlarmSystem implements DelayTimerDelegate{

	private static final int DELAY_IN_SECOND = 30;
    
	private boolean armed;
	private boolean suspended;
    private boolean isReady;
    private DelayTimer delayTimer;
    
    public AlarmSystem() {
    	isReady = true;
    	armed = false;
    	suspended = false;
    	delayTimer = new DelayTimer(this);
    }

    public boolean isArmed() {
        return armed;
    }

	public void arm() throws BadStateException {
		if(!isReady){
			throw new BadStateException();
		} else {
			suspended = true;
			startDelay();
		}
    }
	
    public void disarm() {
    	armed = false;
    	suspended = false;
    }

	public void setNotReady() {
	    isReady = false;
    }

	public void setReady() {
	    isReady = true;
    }

	private void startDelay() {
		delayTimer.startDelay(DELAY_IN_SECOND, this);
    }

	@Override
    public void delayExpired(Object identifier) {
	    if(suspended) {
	    	armed = true;
	    	suspended = false;
	    }
    }
}