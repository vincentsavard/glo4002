package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.communication.ProtocolBuilder;
import ca.ulaval.glo4002.communication.RegistrationCommunicationUnit;
import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class AlarmSystem implements DelayTimerDelegate {

    private static final int DELAY_IN_SECOND = 30;

    private int userID;
    private boolean armed;
    private boolean suspended;
    private boolean isReady;
    private DelayTimer delayTimer;

    public AlarmSystem() {
        isReady = true;
        armed = false;
        suspended = false;
        delayTimer = new DelayTimer(this);
    }

    public void initialize(final String address) {
        RegistrationCommunicationUnit registrationCommunicationUnit = new RegistrationCommunicationUnit();
        ProtocolBuilder protocolBuilder = new ProtocolBuilder();
        protocolBuilder.addClientAddress(address);
        registrationCommunicationUnit.sendRegistrationRequest(protocolBuilder.generate());

        userID = registrationCommunicationUnit.retrieveUserID();
    }

    public boolean isArmed() {
        return armed;
    }

    public void arm() throws BadStateException {
        if (!isReady) {
            throw new BadStateException();
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
        isReady = false;
    }

    public void setReady() {
        isReady = true;
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
}