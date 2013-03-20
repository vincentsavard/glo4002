package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class PoliceTreatment extends EmergencyTreatment {

    public PoliceTreatment() {
        communicator = new Communicator(CommunicationType.POLICE);
    }

    public void processRequest(String userIdPassedByGetRequest) throws UserNotFoundException {
        int userId = Integer.parseInt(userIdPassedByGetRequest);
        if (UserDirectory.getInstance().userExists(userId)) {
            communicator.sendMessageToEmergencyServer(UserDirectory.getInstance().obtainUser(userId));
        } else {
            throw new UserNotFoundException("The ID " + userIdPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }
    }

    // for test purpose only
    protected PoliceTreatment(Communicator communicator) {
        super(communicator);
    }

}
