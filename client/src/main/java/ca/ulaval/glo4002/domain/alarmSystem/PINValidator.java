package ca.ulaval.glo4002.domain.alarmSystem;


public class PINValidator {

    private static final String PIN_FORMAT = "^[0-9]{4,6}$";
    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "#0";

    private String validPIN = DEFAULT_PIN;
    private String previousPIN = DEFAULT_PIN;

    public boolean validatePIN(String typedPIN) {
        return (isValidPIN(typedPIN) || RAPID_PIN == typedPIN);
    }

    public void changePIN(String PIN, String newPIN) {
        if (isValidPIN(PIN)) {
            if (!newPIN.equals(validPIN) && !newPIN.equals(previousPIN)) {
                checkPINFormat(newPIN);
                previousPIN = validPIN;
                validPIN = newPIN;
            } else {
                throw new RecentlyUsedPINException("The new PIN is the same as a recently used PIN.");
            }
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    private void checkPINFormat(String PIN) {
        if (!PIN.matches(PIN_FORMAT)) {
            throw new PINFormatForbiddenException("The format of the PIN is incorrect.");
        }
    }

    private boolean isValidPIN(String PIN) {
        return PIN == validPIN;
    }

}
