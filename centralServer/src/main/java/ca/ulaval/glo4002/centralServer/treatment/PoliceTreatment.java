package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public class PoliceTreatment extends EmergencyTreatment {

    public PoliceTreatment() {
        super();
    }

    // for test purpose only
    protected PoliceTreatment(UsersDirectory usersDirectory,
            CommunicationUnit communicationUnit) {
        super(usersDirectory, communicationUnit);
    }

    public void processRequest(String userIdPassedByGetRequest)
            throws UserNotFoundException {
        int userId = Integer.parseInt(userIdPassedByGetRequest);
        if (usersDirectory.userExists(userId)) {
            communicationUnit.send(usersDirectory.obtainUser(userId));
        } else {
            throw new UserNotFoundException();
        }
    }
}
