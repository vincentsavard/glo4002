package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;

public abstract class EmergencyTreatment {

    protected CommunicationUnit communicationUnit;

    public EmergencyTreatment() {

    }

    // for test purpose only
    protected EmergencyTreatment(CommunicationUnit communicationUnit) {
        this.communicationUnit = communicationUnit;
    }

}
