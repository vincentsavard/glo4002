package ca.ulaval.glo4002.client;

public class SignalHandler {

	protected CommunicationUnit communicationUnit;
	protected SystemState systemState;
	private static SignalHandler detectorSignalHandler;

	public SignalHandler() {
		initHomeConnectionHandler();
		initSystemState();
	}

	public static SignalHandler getInstance() {
		if (detectorSignalHandler == null) {
			detectorSignalHandler = new SignalHandler();
		}
		return detectorSignalHandler;
	}

	public void treatSignal(Signal signalSource) {
		if (signalSource.getDelayToContactEmergency() != 0) {
			// TODO
		}
	}

	public void sendRequestToCentralServer(String info) {
		communicationUnit.sendPostRequest(info);
	}

	protected void initHomeConnectionHandler() {
		communicationUnit = new CommunicationUnit();
	}

	protected void initSystemState() {
		systemState = new SystemState();
	}

	public SystemState getSystemState() {
		return systemState;
	}

	public CommunicationUnit getCommunicationUnit() {
		return communicationUnit;
	}

}
