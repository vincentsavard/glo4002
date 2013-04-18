package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

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
        }
    }

}
