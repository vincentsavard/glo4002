package ca.ulaval.glo4002.domain.alarmSystem;

import ca.ulaval.glo4002.core.timer.DelayTimer;
import ca.ulaval.glo4002.core.timer.DelayTimerDelegate;
import ca.ulaval.glo4002.domain.devices.BadStateException;
import ca.ulaval.glo4002.domain.devices.Siren;

public class AlarmSystem implements DelayTimerDelegate {

    private static final int DELAY_IN_SECONDS = 30;

    private enum StatusType {
        ARMED, SUSPENDED, DISARMED
    };

    private StatusType status = StatusType.DISARMED;
    private boolean ready = true;
    private DelayTimer delayTimer = new DelayTimer(this);
    private Siren siren = new Siren();

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
        siren.deactivate();
    }

    public void setNotReady() {
        ready = false;
    }

    public void setReady() {
        ready = true;
    }

    @Override
    public void delayExpired() {
        if (isInTheProcessOfBeingArmed()) {
            status = StatusType.ARMED;
        }
    }

    private void startDelay() {
        delayTimer.startDelay(DELAY_IN_SECONDS);
    }

    public void activateSiren() {
        siren.activate();
    }

    public void desactivateSiren() {
        siren.deactivate();
    }

    // For test purposes only
    public void armWithoutDelay() {
        if (ready) {
            status = StatusType.ARMED;
        } else {
            throw new BadStateException("System is not ready yet. Alarm system can't be armed.");
        }
    }

    // For test purposes only
    public boolean isSirenRinging() {
        return siren.isRinging();
    }

}