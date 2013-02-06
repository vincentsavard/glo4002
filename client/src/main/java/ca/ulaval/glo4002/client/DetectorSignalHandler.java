package ca.ulaval.glo4002.client;

public class DetectorSignalHandler {

	protected CommunicationUnit communicationUnit;
	protected SystemState systemState;
	private static DetectorSignalHandler detectorSignalHandler;

	public DetectorSignalHandler() {
		initHomeConnectionHandler();
		initSystemState();
	}

	public static DetectorSignalHandler getInstance() {
		if (detectorSignalHandler == null) {
			detectorSignalHandler = new DetectorSignalHandler();
		}
		return detectorSignalHandler;
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

	public void treatSignal(Detector signalSource) {

	}
}
