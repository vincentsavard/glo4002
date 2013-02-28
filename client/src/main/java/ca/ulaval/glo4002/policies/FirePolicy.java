package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class FirePolicy extends Policy {

    public FirePolicy(AlarmSystem alarmSystem) {
        super(alarmSystem);
        new CommunicationUnit(alarmSystem.getUserID(),
                CommunicationUnit.CommunicationType.FIRE);
    }
}
