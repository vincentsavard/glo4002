package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public abstract class Policy {

    protected CommunicationUnit communicationUnit;
    protected AlarmSystem alarmSystem;

    public Policy(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void execute(int zone) {
        if (alarmSystem.isArmed()) {
            sendMessage();
        }
    }

    protected void sendMessage() {
        communicationUnit.send();
    }

    // for test purpose only
    protected Policy(AlarmSystem alarmSystem,
            CommunicationUnit communicationUnit) {
        this.alarmSystem = alarmSystem;
        this.communicationUnit = communicationUnit;
    }
}
