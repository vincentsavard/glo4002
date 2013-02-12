package ca.ulaval.glo4002.client;

import ca.ulaval.glo4002.client.Signal.DetectorType;

public abstract class DetectorAdapter {

    protected SignalHandler detectorSignalHandler;

    public abstract void sendSignalToSignalHandler(DetectorType detectorType,
            int delayToContactEmergency);

    public abstract Signal createSignal(DetectorType detectorType,
            int delayToContactEmergency);

}
