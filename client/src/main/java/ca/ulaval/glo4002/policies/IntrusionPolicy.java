package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class IntrusionPolicy extends Policy {

    public IntrusionPolicy(AlarmSystem alarmSystem) {
        super(alarmSystem);
        int userID = alarmSystem.getUserID();
        communicator = new Communicator(userID);
        targetResource = Communicator.TargetResource.POLICE;
    }

}
