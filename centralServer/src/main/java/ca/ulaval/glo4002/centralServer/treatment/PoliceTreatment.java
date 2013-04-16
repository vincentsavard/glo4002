package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.Alarm;
import ca.ulaval.glo4002.centralServer.user.Alarm.AlarmType;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class PoliceTreatment extends EmergencyTreatment {

    private static final AlarmType ALARM_TYPE = Alarm.AlarmType.INTRUSION;

    public PoliceTreatment(Communicator communicator) {
        this.communicator = communicator;
    }

    public void processRequest(String userIDPassedByGetRequest) throws UserNotFoundException {
        int userID = Integer.parseInt(userIDPassedByGetRequest);
        if (userDirectory.userExists(userID)) {
            communicator.sendMessageToEmergencyServer(CommunicationType.POLICE, userDirectory.obtainUser(userID));
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userID, ALARM_TYPE);
    }

    // for test purpose only
    protected PoliceTreatment(Communicator communicator, UserDirectory userDirectory) {
        super(communicator, userDirectory);
    }

}
