package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.policies.Policy;

public class Detector {

    private Policy policy;
    private int zone;

    public Detector(Policy policy, int zone) {
        this.policy = policy;
        this.zone = zone;
    }

    public void trigger() {
        policy.executeProcedure(zone);
    }

}
