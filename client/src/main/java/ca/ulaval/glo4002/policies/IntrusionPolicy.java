package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.IntrusionCommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class IntrusionPolicy extends Policy {

    public IntrusionPolicy(AlarmSystem alarmSystem) {
        super(alarmSystem);
        communicationUnit = new IntrusionCommunicationUnit();
    }

    @Override
    public void executePolicy() {

    }

}
