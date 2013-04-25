package ca.ulaval.glo4002.domain.devices;

import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;
import ca.ulaval.glo4002.domain.alarmSystem.InvalidPINException;
import ca.ulaval.glo4002.domain.alarmSystem.PINValidator;

public class Keypad {

    private AlarmSystem alarmSystem;
    private PINValidator validator;

    public Keypad(AlarmSystem alarmSystem, PINValidator validator) {
        this.alarmSystem = alarmSystem;
        this.validator = validator;
    }

    public void armSystem(String PIN) {
        if (validator.validatePIN(PIN)) {
            alarmSystem.armWithThirtySecondsDelay();
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    public void disarmSystem(String PIN) {
        if (validator.validatePIN(PIN)) {
            alarmSystem.disarm();
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    public void requestPINChange(String currentPIN, String newPIN) {
        validator.changePIN(currentPIN, newPIN);
    }

}
