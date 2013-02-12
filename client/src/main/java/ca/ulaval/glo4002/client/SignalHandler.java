package ca.ulaval.glo4002.client;

import java.util.HashMap;

public class SignalHandler implements DelayTimerDelegate {

    private CommunicationUnit communicationUnit;
    private SystemState systemState; // FIXME Use this or remove it
    private DelayTimer delayManager;
    private MessageEncoder messageEncoder;

    private static SignalHandler detectorSignalHandler;

    private SignalHandler() {
        systemState = new SystemState();
        communicationUnit = new CommunicationUnit();
        delayManager = new DelayTimer(this);
        messageEncoder = new MessageEncoder();
    }

    protected SignalHandler(DelayTimer delayManager,
            CommunicationUnit communicationUnit, SystemState systemState,
            MessageEncoder messageEncoder) {
        this.systemState = systemState;
        this.communicationUnit = communicationUnit;
        this.delayManager = delayManager;
        this.messageEncoder = messageEncoder;
    }

    public static SignalHandler getInstance() {
        if (detectorSignalHandler == null) {
            detectorSignalHandler = new SignalHandler();
        }
        return detectorSignalHandler;
    }

    public void treatSignal(Signal signalSource) {
        if (signalSource.getDelayToContactEmergency() != 0) {
            delayManager.startDelay(signalSource.getDelayToContactEmergency(),
                    signalSource);
        } else {
            delayManager.cancelDelay();
            sendRequestToCentralServer(signalSource);
        }
    }

    public void sendRequestToCentralServer(Signal signal) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("Detector", signal.getDetectorType().name());
        String encodedData = messageEncoder.generateEncodedMessage(data);
        communicationUnit.sendPostRequest(encodedData);
    }

    @Override
    public void delayExpired(Object identifier) {
        if (!(identifier instanceof Signal)) {
            throw new RuntimeException("Passed object must be a Signal");
        }
        sendRequestToCentralServer((Signal) identifier);
    }

    public String getLastResponseReceived() {
        return communicationUnit.getLastResponse();
    }

}
