package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class AlarmListTreatment {

    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    public String retrieveLogFromUser(String userIDPassedByGetRequest) {
        int userID = Integer.parseInt(userIDPassedByGetRequest);
        String log;
        if (userDirectory.userExists(userID)) {
            log = userDirectory.obtainUser(userID).createLogForAllAlarms();
        } else {
            throw new UserNotFoundException("The ID " + userIDPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }

        return log;
    }

}
