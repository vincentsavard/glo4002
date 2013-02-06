package ca.ulaval.glo4002.client;

public class Detector {

	protected DetectorSignalHandler detectorSignalHandler;

	public Detector() {
		initDetector();
	}

	protected void initDetector() {
		detectorSignalHandler = DetectorSignalHandler.getInstance();
	}

	public void trigger() {
		sendSignal();
	}

	private void sendSignal() {
		detectorSignalHandler.treatSignal(this);
	}

}