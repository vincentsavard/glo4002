package ca.ulaval.glo4002.devices;

public class Keypad {

    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "#0";

    private String validPIN = DEFAULT_PIN;
    private AlarmSystem alarmSystem;

    public Keypad(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void armSystem(String PIN) {
        if (isPINValid(PIN)) {
            alarmSystem.armWithThirtySecondsDelay();
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    public void disarmSystem(String PIN) {
        if (isPINValid(PIN)) {
            alarmSystem.disarm();
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    public void changePIN(String PIN, String newPIN) {
        if (isPINValid(PIN)) {
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

    // Protected for testing purposes
    protected boolean isPINValid(String newPIN) {
        return (validPIN == newPIN || RAPID_PIN == newPIN);
    }

}
