package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;

public class UserRegistrar {

    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    public int generateUserID() {
        int newUserID = userDirectory.generateNewID();
        return newUserID;
    }

    public void registerUser(int newUserID, String userInformation) {
        User newUser = createNewUser(newUserID, userInformation);
        userDirectory.addUser(newUser);
    }

    private User createNewUser(int newUserID, String userInformation) {
        User newUser = new User(newUserID, userInformation);
        return newUser;
    }

}
