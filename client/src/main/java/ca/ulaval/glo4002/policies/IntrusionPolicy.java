package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class IntrusionPolicy extends Policy {

    public IntrusionPolicy(AlarmSystem alarmSystem, Communicator communicator) {
        super(alarmSystem);
        this.communicator = communicator;
        targetResource = Communicator.TargetResource.POLICE;
    }

}
