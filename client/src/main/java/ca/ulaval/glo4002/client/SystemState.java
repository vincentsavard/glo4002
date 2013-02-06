package ca.ulaval.glo4002.client;

public class SystemState {
	private boolean armed = false;

	public boolean systemIsArmed() {
		return armed;
	}

	public void armSystem() {
		armed = true;
	}

}
