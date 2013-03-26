package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;

public abstract class EmergencyTreatment {

    protected Communicator communicator;
    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    public EmergencyTreatment() {

    }

    // for test purpose only
    protected EmergencyTreatment(Communicator communicator, UserDirectory userDirectory) {
        this.communicator = communicator;
        this.userDirectory = userDirectory;
    }

}
