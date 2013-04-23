package ca.ulaval.glo4002.domain.policies;

import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;

public abstract class Policy {

    protected Communicator communicator;
    protected Communicator.TargetResource targetResource;
    protected AlarmSystem alarmSystem;

    public Policy(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void executeProcedure(int zone) {
        if (alarmSystem.isArmed()) {
            sendMessage(targetResource);
        }
    }

    protected void sendMessage(Communicator.TargetResource targetResource) {
        communicator.sendMessageToCentralServer(targetResource);
    }

    // For test purposes only
    protected Policy(AlarmSystem alarmSystem, Communicator communicator) {
        this.alarmSystem = alarmSystem;
        this.communicator = communicator;
    }

}
