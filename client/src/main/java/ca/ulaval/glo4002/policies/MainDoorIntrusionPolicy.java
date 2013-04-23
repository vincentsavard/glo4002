package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class MainDoorIntrusionPolicy extends Policy implements DelayTimerDelegate {

    private static final int INTRUSION_DELAY_IN_SECONDS = 30;

    private DelayTimer delayTimer;

    public MainDoorIntrusionPolicy(AlarmSystem alarmSystem, Communicator communicator) {
        super(alarmSystem);
        this.communicator = communicator;
        delayTimer = new DelayTimer(this);
        targetResource = Communicator.TargetResource.POLICE;
    }

    @Override
    public void executeProcedure(int zone) {
        if (alarmSystem.isArmed()) {
            delayTimer.startDelay(INTRUSION_DELAY_IN_SECONDS);
        }
    }

    @Override
    public void delayExpired() {
        if (alarmSystem.isArmed()) {
            communicator.sendMessageToCentralServer(targetResource);
        }
    }

    // For test purpose only
    protected MainDoorIntrusionPolicy(AlarmSystem alarmSystem, Communicator communicator, DelayTimer delayTimer) {
        super(alarmSystem);
        this.communicator = communicator;
        this.delayTimer = delayTimer;
    }

}
