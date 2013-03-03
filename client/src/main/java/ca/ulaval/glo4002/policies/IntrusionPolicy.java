package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class IntrusionPolicy extends Policy {

    public IntrusionPolicy(final AlarmSystem alarmSystem) {
        super(alarmSystem);
        communicationUnit = new CommunicationUnit(alarmSystem.getUserID(),
                CommunicationUnit.CommunicationType.INTRUSION);
    }
}
