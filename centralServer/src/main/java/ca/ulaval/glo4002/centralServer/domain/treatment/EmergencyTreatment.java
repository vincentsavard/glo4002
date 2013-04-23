package ca.ulaval.glo4002.centralServer.domain.treatment;

import java.util.Date;

import ca.ulaval.glo4002.centralServer.core.communication.Communicator;
import ca.ulaval.glo4002.centralServer.core.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.domain.user.Alarm;
import ca.ulaval.glo4002.centralServer.domain.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.domain.user.UserDirectoryLocator;
import ca.ulaval.glo4002.centralServer.domain.user.UserNotFoundException;

public class EmergencyTreatment {

    private CommunicationType communicationType;
    protected Communicator communicator;
    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    public EmergencyTreatment(CommunicationType communicationType, Communicator communicator) {
        this.communicationType = communicationType;
        this.communicator = communicator;
    }

    protected void addAlarmToUserList(int userID) {
        Date currentDate = new Date();
        Alarm alarm = new Alarm(communicationType, currentDate);
        userDirectory.obtainUser(userID).addAlarm(alarm);
    }

    public void processRequest(String userIDPassedByPOSTRequest) {
        int userID = Integer.parseInt(userIDPassedByPOSTRequest);
        if (userDirectory.userExists(userID)) {
            communicator.sendMessageToEmergencyServer(communicationType, userDirectory.obtainUser(userID));
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByPOSTRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userID);
    }

    public void processRequest(String userIDPassedByPOSTRequest, String zonePassedByPOSTRequest) {
        int userID = Integer.parseInt(userIDPassedByPOSTRequest);
        if (userDirectory.userExists(userID)) {
            communicator.sendMessageToEmergencyServer(communicationType, userDirectory.obtainUser(userID),
                                                      zonePassedByPOSTRequest);
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByPOSTRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userID);
    }

    // For test purposes only
    protected EmergencyTreatment(CommunicationType communicationType, Communicator communicator,
                                 UserDirectory userDirectory) {
        this.communicationType = communicationType;
        this.communicator = communicator;
        this.userDirectory = userDirectory;
    }

}
