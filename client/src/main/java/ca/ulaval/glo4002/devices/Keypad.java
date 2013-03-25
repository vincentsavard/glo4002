package ca.ulaval.glo4002.devices;

public class Keypad {

    private AlarmSystem alarmSystem;

    public Keypad(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void armSystem(String PIN) {
        if (alarmSystem.validatePIN(PIN)) {
            alarmSystem.armWithThirtySecondsDelay();
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    public void disarmSystem(String PIN) {
        if (alarmSystem.validatePIN(PIN)) {
            alarmSystem.disarm();
        } else {
            throw new InvalidPINException("The PIN is invalid.");
        }
    }

    public void requestPINChange(String PIN, String newPIN) {
        alarmSystem.changePIN(PIN, newPIN);
    }

}
