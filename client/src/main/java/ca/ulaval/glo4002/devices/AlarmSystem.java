package ca.ulaval.glo4002.devices;

import java.util.HashMap;

import ca.ulaval.glo4002.communication.ProtocolBuilder;
import ca.ulaval.glo4002.communication.RegistrationCommunicator;
import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class AlarmSystem implements DelayTimerDelegate {

    private static final int DELAY_IN_SECOND = 30;
    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "#0";

    private String validPIN = DEFAULT_PIN;
    private int userID;
    private boolean armed = false;
    private boolean suspended = false;
    private boolean ready = true;
    private DelayTimer delayTimer = new DelayTimer(this);

    public void registerToCentralServer(String address) {
        RegistrationCommunicator registrationCommunicator = new RegistrationCommunicator();
        HashMap<String, String> attributes = buildProtocol(address);

        registrationCommunicator.sendRegistrationRequest(attributes);
        userID = registrationCommunicator.retrieveUserID();
    }

    public boolean validatePIN(String typedPIN) {
        return (isValidPIN(typedPIN) || RAPID_PIN == typedPIN);
    }

    public void changePIN(String PIN, String newPIN) {
        if (isValidPIN(PIN)) {
            checkPINFormat(newPIN);
            validPIN = newPIN;
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    private void checkPINFormat(String PIN) {
        if (!PIN.matches("^[0-9]{5}$")) {
            throw new PINFormatForbiddenException("The format of the PIN is incorrect.");
        }
    }

    private boolean isValidPIN(String PIN) {
        return PIN == validPIN;
    }

    public boolean isArmed() {
        return armed;
    }

    public boolean isInTheProcessOfBeingArmed() {
        return suspended;
    }

    public void armWithThirtySecondsDelay() {
        if (ready) {
            suspended = true;
            startDelay();
        } else {
            throw new BadStateException("System is not ready yet. Alarm system can't be armed.");
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

    // For test purpose only
    public void armWithoutDelay() {
        if (ready) {
            armed = true;
        } else {
            throw new BadStateException("System is not ready yet. Alarm system can't be armed.");
        }
    }

}