package ca.ulaval.glo4002.devices;

public class AlarmSystem {

    private enum SystemStatus {
        ARMED, DISARMED, EXIT_DELAY
    };

    private static final String CORRECT_NIP = "1234";
    private static final String RAPID_NIP = "00";
    private boolean ready = true;
    private SystemStatus status = SystemStatus.DISARMED;

    public boolean isArmed() {
        return status == SystemStatus.ARMED;
    }

    private void changeStatusToArmed() {
        status = SystemStatus.ARMED;
    }

    private void changeStatusToDisarmed() {
        status = SystemStatus.DISARMED;
    }

    private void changeStatusToExitDelay() {
        status = SystemStatus.EXIT_DELAY;
    }

    public boolean isReady() {
        return ready;
    }

    private void armSystem() {
        if (isReady()) {
            changeStatusToArmed();
        }
    }

    private void disarmSystem() {
        changeStatusToDisarmed();
    }

    private boolean isNIPValid(String nip) {
        if (isArmed()) {
            return nip.equals(CORRECT_NIP);
        } else {
            return nip.equals(CORRECT_NIP) || nip.equals(RAPID_NIP);
        }
    }

    public void handleKeypadEntry(String nip) {
        if (isNIPValid(nip)) {
            if (!isArmed()) {
                armSystem();
            } else {
                disarmSystem();
            }
        }
    }
}
