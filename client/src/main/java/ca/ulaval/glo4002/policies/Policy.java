package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

public abstract class Policy {

    protected Communicator communicator;
    protected AlarmSystem alarmSystem;

    public Policy(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void executeProcedure() {
        if (alarmSystem.isArmed()) {
            sendMessage();
        }
    }

    protected void sendMessage() {
        communicator.sendMessageToCentralServer();
    }

    // For test purpose only
    protected Policy(AlarmSystem alarmSystem, Communicator communicator) {
        this.alarmSystem = alarmSystem;
        this.communicator = communicator;
    }

}
