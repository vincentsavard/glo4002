package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;
import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class PoliceTreatment extends EmergencyTreatment {

    public PoliceTreatment() {
        communicationUnit = new CommunicationUnit(CommunicationType.POLICE);
    }

    public void processRequest(String userIdPassedByGetRequest) throws UserNotFoundException {
        int userId = Integer.parseInt(userIdPassedByGetRequest);
        if (UserDirectory.getInstance().userExists(userId)) {
            communicationUnit.sendMessageToEmergencyServer(UserDirectory.getInstance().obtainUser(userId));
        } else {
            throw new UserNotFoundException("The ID " + userIdPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }
    }

    // for test purpose only
    protected PoliceTreatment(CommunicationUnit communicationUnit) {
        super(communicationUnit);
    }

}
