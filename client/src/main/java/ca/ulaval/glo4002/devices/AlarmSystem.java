package ca.ulaval.glo4002.devices;

import java.util.HashMap;

import ca.ulaval.glo4002.communication.ProtocolBuilder;
import ca.ulaval.glo4002.communication.RegistrationCommunicationUnit;
import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class AlarmSystem implements DelayTimerDelegate {

    private static final int DELAY_IN_SECOND = 30;

    private int userID;
    private boolean armed;
    private boolean suspended;
    private boolean ready;
    private DelayTimer delayTimer;

    public AlarmSystem() {
        ready = true;
        armed = false;
        suspended = false;
        delayTimer = new DelayTimer(this);
    }

    public void registerToCentralServer(final String address) {
        RegistrationCommunicationUnit registrationCommunicationUnit = new RegistrationCommunicationUnit();
        HashMap<String, String> attributes = buildProtocol(address);

        registrationCommunicationUnit.sendRegistrationRequest(attributes);

        userID = registrationCommunicationUnit.retrieveUserID();
    }

    public boolean isArmed() {
        return armed;
    }

    public void arm() throws BadStateException {
        if (!ready) {
            throw new BadStateException(
                    "System is not ready yet. Alarm system can't be armed.");
        } else {
            suspended = true;
            startDelay();
        }
    }

    public void disarm() {
        armed = false;
        suspended = false;
    }

    public void setNotReady() {
        ready = false;
    }

    public void setReady() {
        ready = true;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public void delayExpired() {
        if (suspended) {
            armed = true;
            suspended = false;
        }
    }

    private void startDelay() {
        delayTimer.startDelay(DELAY_IN_SECOND);
    }

    private HashMap<String, String> buildProtocol(String address) {
        ProtocolBuilder protocolBuilder = new ProtocolBuilder();
        protocolBuilder.addClientAddress(address);

        return protocolBuilder.generate();
    }
}