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

    public void processRequest(int userIDPassedByPOSTRequest) {
        if (userDirectory.userExists(userIDPassedByPOSTRequest)) {
            communicator.sendMessageToEmergencyServer(communicationType,
                                                      userDirectory.obtainUser(userIDPassedByPOSTRequest));
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByPOSTRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userIDPassedByPOSTRequest);
    }

    public void processRequest(int userIDPassedByPOSTRequest, String zonePassedByPOSTRequest) {
        if (userDirectory.userExists(userIDPassedByPOSTRequest)) {
            communicator.sendMessageToEmergencyServer(communicationType,
                                                      userDirectory.obtainUser(userIDPassedByPOSTRequest),
                                                      zonePassedByPOSTRequest);
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByPOSTRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userIDPassedByPOSTRequest);
    }

    // For test purposes only
    protected EmergencyTreatment(CommunicationType communicationType, Communicator communicator,
                                 UserDirectory userDirectory) {
        this.communicationType = communicationType;
        this.communicator = communicator;
        this.userDirectory = userDirectory;
    }

}
