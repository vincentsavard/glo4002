package ca.ulaval.glo4002.client;

import ca.ulaval.glo4002.client.Signal.DetectorType;

public class DetectionSignalAdapter {

	protected SignalHandler detectorSignalHandler;

	public DetectionSignalAdapter() {
		detectorSignalHandler = SignalHandler.getInstance();
	}

	protected DetectionSignalAdapter(SignalHandler signalHandler) {
		detectorSignalHandler = signalHandler;
	}

	public void sendSignalToSignalHandler(DetectorType detectorType,
			int delayToContactEmergency) {
		Signal signalSource = createSignal(detectorType,
				delayToContactEmergency);
		detectorSignalHandler.treatSignal(signalSource);
	}

	private Signal createSignal(DetectorType detectorType,
			int delayToContactEmergency) {
		return new Signal(detectorType, delayToContactEmergency);
	}

}
