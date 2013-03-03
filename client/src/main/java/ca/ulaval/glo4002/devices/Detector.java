package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.policies.Policy;

public class Detector {

    private Policy policy;

    public Detector(final Policy policy) {
        this.policy = policy;
    }

    public void trigger() {
        policy.executeProcedure();
    }
}
