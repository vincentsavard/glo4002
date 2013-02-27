package ca.ulaval.glo4002.devices;

public class Siren {

	private boolean isActivated;
	
	public Siren() {
		isActivated = false;
	}

	protected boolean isRigging() {
	    return isActivated;
    }

	public void activate() {
		isActivated = true;
    }

	public void deactivate() {
		isActivated = false;
    }

}
