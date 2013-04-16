package ca.ulaval.glo4002.centralServer.treatment;

import java.util.Date;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.Alarm;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

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

    public void processRequest(String userIDPassedByGetRequest) throws UserNotFoundException {
        int userID = Integer.parseInt(userIDPassedByGetRequest);
        if (userDirectory.userExists(userID)) {
            communicator.sendMessageToEmergencyServer(communicationType, userDirectory.obtainUser(userID));
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userID);
    }

    public void processRequest(String userIDPassedByGetRequest, String zonePassedByGetRequest) {
        int userID = Integer.parseInt(userIDPassedByGetRequest);
        if (userDirectory.userExists(userID)) {
            communicator.sendMessageToEmergencyServer(communicationType, userDirectory.obtainUser(userID),
                                                      zonePassedByGetRequest);
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userID);
    }

    // for test purpose only
    protected EmergencyTreatment(CommunicationType communicationType, Communicator communicator,
                                 UserDirectory userDirectory) {
        this.communicationType = communicationType;
        this.communicator = communicator;
        this.userDirectory = userDirectory;
    }

}
