package ca.ulaval.glo4002.devices;

public class Keypad {

    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "00";

    private String validPIN;
    private AlarmSystem alarmSystem;

    public Keypad(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
        this.validPIN = DEFAULT_PIN;
    }

    public void armSystem(String PIN) throws BadStateException, InvalidPINException {
        if (isPINValid(PIN)) {
            alarmSystem.armWithThirtySecondsDelay();
        } else {
            throw new InvalidPINException("The PIN is wrong.");
        }
    }

    public void disarmSystem(String PIN) throws InvalidPINException {
        if (isPINValid(PIN)) {
            alarmSystem.disarm();
        } else {
            throw new InvalidPINException("The PIN is wrong.");
        }
    }

    public void changePIN(String PIN, String newPIN) throws InvalidPINException {
        if (isPINValid(PIN)) {
            checkPINFormat(newPIN);
            validPIN = newPIN;
        } else {
            throw new InvalidPINException("The PIN is wrong.");
        }
    }

    // Protected for testing purposes
    protected boolean isPINValid(String newPIN) {
        return (validPIN == newPIN || RAPID_PIN == newPIN);
    }

    private void checkPINFormat(String PIN) {
        if (!PIN.matches("^[0-9]{5}$")) {
            throw new PINFormatForbiddenException("The format of the PIN is incorrect.");
        }
    }
}
