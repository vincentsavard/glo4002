package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class PolicyFactory {

    public static enum PolicyType {
        FIRE_POLICY, INTRUSION_POLICY, MAIN_DOOR_INTRUSION_POLICY
    };

    protected AlarmSystem alarmSystem;
    protected Communicator communicator;

    public PolicyFactory(AlarmSystem alarmSystem, Communicator communicator) {
        this.alarmSystem = alarmSystem;
        this.communicator = communicator;
    }

    public Policy createPolicy(PolicyType policyType) {
        if (policyType == PolicyType.FIRE_POLICY) {
            return new FirePolicy(alarmSystem, communicator);
        } else if (policyType == PolicyType.INTRUSION_POLICY) {
            return new IntrusionPolicy(alarmSystem, communicator);
        } else if (policyType == PolicyType.MAIN_DOOR_INTRUSION_POLICY) {
            return new MainDoorIntrusionPolicy(alarmSystem, communicator);
        } else {
            throw new InvalidPolicyException("Invalid policy type at construction.");
        }
    }

}
