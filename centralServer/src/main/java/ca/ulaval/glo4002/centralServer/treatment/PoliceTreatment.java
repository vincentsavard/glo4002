package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;
import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public class PoliceTreatment extends EmergencyTreatment {

    public PoliceTreatment() {
        super();
        communicationUnit = new CommunicationUnit(CommunicationType.POLICE);
    }

    public void processRequest(String userIdPassedByGetRequest) throws UserNotFoundException {
        int userId = Integer.parseInt(userIdPassedByGetRequest);
        if (usersDirectory.userExists(userId)) {
            communicationUnit.sendMessageToEmergencyServer(usersDirectory.obtainUser(userId));
        } else {
            throw new UserNotFoundException("The ID " + userIdPassedByGetRequest
                                            + " was not found in the UsersDirectory while processing request.");
        }
    }

    // for test purpose only
    protected PoliceTreatment(UsersDirectory usersDirectory, CommunicationUnit communicationUnit) {
        super(usersDirectory, communicationUnit);
    }
}
