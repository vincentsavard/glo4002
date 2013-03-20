package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.Communicator;

public abstract class EmergencyTreatment {

    protected Communicator communicator;

    public EmergencyTreatment() {

    }

    // for test purpose only
    protected EmergencyTreatment(Communicator communicator) {
        this.communicator = communicator;
    }

}
