package ca.ulaval.glo4002.client;

public class Home {

	protected HomeConnectionHandler homeConnectionHandler;
	private boolean isArmed = false;
	private boolean mainDoorIsOpened = false;

	public Home() {
		setHomeConnectionHandler();
	}

	public boolean systemIsArmed() {
		return isArmed;
	}

	public void armSystem() {
		isArmed = true;
	}

	public void openMainDoor() {
		mainDoorIsOpened = true;
		if (systemIsArmed()) {
			detectIntrusion("Main door is open");
		}
	}

	public boolean mainDoorIsOpen() {
		return mainDoorIsOpened;
	}

	private void detectIntrusion(String info) {
		sendRequestToServer(info);

	}

	private void sendRequestToServer(String info) {
		homeConnectionHandler.sendPostRequest(info);
	}

	protected void setHomeConnectionHandler() {
		homeConnectionHandler = new HomeConnectionHandler();
	}

	public HomeConnectionHandler getHomeConnectionHandler() {
		return homeConnectionHandler;
	}
}
