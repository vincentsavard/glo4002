package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.devices.AlarmSystem;

public abstract class PolicyFactory {

    protected AlarmSystem alarmSystem;

    public PolicyFactory(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public abstract Policy createPolicy();
}
