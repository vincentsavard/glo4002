package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.devices.AlarmSystem;

public class PolicyFactory {

    protected AlarmSystem alarmSystem;

    public static enum PolicyType {
        FIRE_POLICY, INTRUSION_POLICY, MAIN_DOOR_INTRUSION_POLICY
    };

    public PolicyFactory(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public Policy createPolicy(PolicyType policyType) {
        if (policyType == PolicyType.FIRE_POLICY) {
            return new FirePolicy(alarmSystem);
        } else if (policyType == PolicyType.INTRUSION_POLICY) {
            return new IntrusionPolicy(alarmSystem);
        } else if (policyType == PolicyType.MAIN_DOOR_INTRUSION_POLICY) {
            return new MainDoorIntrusionPolicy(alarmSystem);
        } else {
            throw new RuntimeException("Wrong policy type");
        }

    }
}
