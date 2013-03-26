package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class AlarmSystem implements DelayTimerDelegate {
    
    private enum StatusType { ARMED, SUSPENDED, DISARMED };

    private static final int DELAY_IN_SECOND = 30;
    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "#0";

    private String validPIN = DEFAULT_PIN;
    private int userID;
    private StatusType status = StatusType.DISARMED;
    private boolean ready = true;
    private DelayTimer delayTimer = new DelayTimer(this);
    
    public AlarmSystem(int userID) {
        this.userID = userID;
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
        return status == StatusType.ARMED;
    }

    public boolean isInTheProcessOfBeingArmed() {
        return status == StatusType.SUSPENDED;
    }

    public void armWithThirtySecondsDelay() {
        if (ready) {
            status = StatusType.SUSPENDED;
            startDelay();
        } else {
            throw new BadStateException("System is not ready yet. Alarm system can't be armed.");
        }
    }

    public void disarm() {
        status = StatusType.DISARMED;
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
        if (isInTheProcessOfBeingArmed()) {
            status = StatusType.ARMED;
        }
    }

    private void startDelay() {
        delayTimer.startDelay(DELAY_IN_SECOND);
    }

    // For test purpose only
    public void armWithoutDelay() {
        if (ready) {
            status = StatusType.ARMED;
        } else {
            throw new BadStateException("System is not ready yet. Alarm system can't be armed.");
        }
    }

}