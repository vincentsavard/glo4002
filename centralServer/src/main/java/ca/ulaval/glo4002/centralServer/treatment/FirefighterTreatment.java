package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.Alarm;
import ca.ulaval.glo4002.centralServer.user.Alarm.AlarmType;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class FirefighterTreatment extends EmergencyTreatment {

    private static final AlarmType ALARM_TYPE = Alarm.AlarmType.FIRE;

    public FirefighterTreatment(Communicator communicator) {
        this.communicator = communicator;
    }

    public void processRequest(String userIDPassedByGetRequest, String zonePassedByGetRequest) {
        int userID = Integer.parseInt(userIDPassedByGetRequest);
        if (userDirectory.userExists(userID)) {
            communicator.sendMessageToEmergencyServer(CommunicationType.FIRE, userDirectory.obtainUser(userID),
                                                      zonePassedByGetRequest);
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userID, ALARM_TYPE);
    }

    // for test purpose only
    protected FirefighterTreatment(Communicator communicator, UserDirectory userDirectory) {
        super(communicator, userDirectory);
    }

}
