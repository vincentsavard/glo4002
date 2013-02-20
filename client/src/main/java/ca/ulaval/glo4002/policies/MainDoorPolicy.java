package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.IntrusionCommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class MainDoorPolicy extends Policy implements DelayTimerDelegate {

    public MainDoorPolicy(AlarmSystem alarmSystem) {
        super(alarmSystem);
        communicationUnit = new IntrusionCommunicationUnit();

    }

    @Override
    public void executePolicy() {
    }

    @Override
    public void delayExpired(Object identifier) {
    }

}
