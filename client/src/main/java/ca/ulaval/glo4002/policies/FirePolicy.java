package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.FireCommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class FirePolicy extends Policy {

    public FirePolicy(AlarmSystem alarmSystem) {
        super(alarmSystem);
        communicationUnit = new FireCommunicationUnit();
    }

    @Override
    public void execute(int zone) {
    }

}
