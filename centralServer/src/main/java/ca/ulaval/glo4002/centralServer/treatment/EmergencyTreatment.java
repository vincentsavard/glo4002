package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public abstract class EmergencyTreatment {

    protected CommunicationUnit communicationUnit;
    protected UsersDirectory usersDirectory;

    public EmergencyTreatment() {
        usersDirectory = UsersDirectory.getInstance();
        communicationUnit = new CommunicationUnit();
    }

    public void send() {
        // FIXME
    }

    // for test purpose only
    protected EmergencyTreatment(final UsersDirectory usersDirectory, final CommunicationUnit communicationUnit) {
        this.usersDirectory = usersDirectory;
        this.communicationUnit = communicationUnit;
    }
}
