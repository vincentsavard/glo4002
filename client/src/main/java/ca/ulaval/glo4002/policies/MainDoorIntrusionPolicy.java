package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class MainDoorIntrusionPolicy extends Policy implements DelayTimerDelegate {

    private static final int INTRUSION_DELAY_IN_SECONDS = 30;

    private DelayTimer delayTimer;

    public MainDoorIntrusionPolicy(final AlarmSystem alarmSystem) {
        super(alarmSystem);
        communicationUnit = new CommunicationUnit(alarmSystem.getUserID(),
                                                  CommunicationUnit.CommunicationType.INTRUSION);
        delayTimer = new DelayTimer(this);
    }

    @Override
    public void execute() {
        if (alarmSystem.isArmed()) {
            delayTimer.startDelay(INTRUSION_DELAY_IN_SECONDS);
        }
    }

    @Override
    public void delayExpired() {
        if (alarmSystem.isArmed()) {
            communicationUnit.send();
        }
    }

    // for test purpose only
    protected MainDoorIntrusionPolicy(final AlarmSystem alarmSystem, final CommunicationUnit communicationUnit,
                                      final DelayTimer delayTimer) {
        super(alarmSystem);
        this.communicationUnit = communicationUnit;
        this.delayTimer = delayTimer;
    }
}
