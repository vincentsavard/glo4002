package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.devices.AlarmSystem;

public class IntrusionPolicyFactory extends PolicyFactory {

    public IntrusionPolicyFactory(AlarmSystem alarmSystem) {
        super(alarmSystem);
    }

    @Override
    public Policy createPolicy() {
        return new IntrusionPolicy(alarmSystem);
    }

}
