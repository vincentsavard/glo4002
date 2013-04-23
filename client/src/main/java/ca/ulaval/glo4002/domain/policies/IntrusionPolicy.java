package ca.ulaval.glo4002.domain.policies;

import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;

public class IntrusionPolicy extends Policy {

    public IntrusionPolicy(AlarmSystem alarmSystem, Communicator communicator) {
        super(alarmSystem);
        this.communicator = communicator;
        targetResource = Communicator.TargetResource.POLICE;
    }

}
