package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class MainDoorIntrusionPolicy extends Policy implements
        DelayTimerDelegate {

    private DelayTimer delayTimer;
    private final int INTRUSION_DELAY_IN_SECONDS = 30;

    public MainDoorIntrusionPolicy(AlarmSystem alarmSystem) {
        super(alarmSystem);
        communicationUnit = new CommunicationUnit(alarmSystem.getUserID(),
                CommunicationUnit.CommunicationType.INTRUSION);
        delayTimer = new DelayTimer(this);
    }

    // for test purpose only
    protected MainDoorIntrusionPolicy(AlarmSystem alarmSystem,
            CommunicationUnit communicationUnit, DelayTimer delayTimer) {
        super(alarmSystem);
        this.communicationUnit = communicationUnit;
        this.delayTimer = delayTimer;
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

}
