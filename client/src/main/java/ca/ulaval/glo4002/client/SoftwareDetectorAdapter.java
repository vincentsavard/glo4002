package ca.ulaval.glo4002.client;

import ca.ulaval.glo4002.client.Signal.DetectorType;

public class SoftwareDetectorAdapter extends DetectorAdapter {
  public SoftwareDetectorAdapter() {
    detectorSignalHandler = SignalHandler.getInstance();
  }

  protected SoftwareDetectorAdapter(SignalHandler signalHandler) {
    detectorSignalHandler = signalHandler;
  }

  public void sendSignalToSignalHandler(DetectorType detectorType,
      int delayToContactEmergency) {
    Signal signalSource = createSignal(detectorType, delayToContactEmergency);
    detectorSignalHandler.treatSignal(signalSource);
  }

  public Signal createSignal(DetectorType detectorType,
      int delayToContactEmergency) {
    return new Signal(detectorType, delayToContactEmergency);
  }
}
