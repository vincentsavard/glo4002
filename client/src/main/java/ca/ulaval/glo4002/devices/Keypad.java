package ca.ulaval.glo4002.devices;

public class Keypad {

    private static final String DEFAULT_PIN = "12345";

    private String validPIN;
    private AlarmSystem alarmSystem;

    public Keypad(final AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
        this.validPIN = DEFAULT_PIN;
    }

    public void armSystem(final String pin) throws BadStateException,
            InvalidPINException {
        validatePIN(pin);
        alarmSystem.arm();
    }

    public void disarmSystem(final String pin) throws InvalidPINException {
        validatePIN(pin);
        alarmSystem.disarm();
    }

    public void changePIN(final String pin, final String newPIN)
            throws InvalidPINException {
        validatePIN(pin);
        checkPINFormat(newPIN);
        validPIN = newPIN;
    }

    protected boolean isPINValid(final String newPin) {
        return validPIN == newPin;
    }

    private void validatePIN(final String pin) throws InvalidPINException {
        if (pin != validPIN) {
            throw new InvalidPINException(
                    "The PIN submitted doesn't match with the PIN of the alarm system");
        }
    }

    private void checkPINFormat(final String pin) {
        if (!pin.matches("^[0-9]{5}$")) {
            throw new PINFormatForbiddenException(
                    "The format of the PIN is incorrect.");
        }
    }
}
