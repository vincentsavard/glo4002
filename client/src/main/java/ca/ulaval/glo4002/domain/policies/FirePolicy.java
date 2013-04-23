package ca.ulaval.glo4002.domain.policies;

import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;

public class FirePolicy extends Policy {

    public FirePolicy(AlarmSystem alarmSystem, Communicator communicator) {
        super(alarmSystem);
        this.communicator = communicator;
        targetResource = Communicator.TargetResource.FIRE;
    }

    @Override
    public void executeProcedure(int zone) {
        if (alarmSystem.isArmed()) {
            communicator.sendMessageToCentralServer(targetResource, String.valueOf(zone));
            alarmSystem.activateSiren();
        }
    }

}
