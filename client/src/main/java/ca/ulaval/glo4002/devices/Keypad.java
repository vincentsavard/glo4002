package ca.ulaval.glo4002.devices;

public class Keypad {

    private AlarmSystem alarmSystem;

    public void sendNIPtoSystem(String nip) {
        alarmSystem.handleKeypadEntry(nip);
    }
}
