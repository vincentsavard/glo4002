package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;

public class UserRegistrar {

    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    public int generateUserID() {
        int newUserId = userDirectory.generateNewId();
        return newUserId;
    }

    public void registerUser(int newUserId, String userInformation) {
        User newUser = createNewUser(newUserId, userInformation);
        userDirectory.addUser(newUser);
    }

    private User createNewUser(int newUserId, String userInformation) {
        User newUser = new User(newUserId, userInformation);
        return newUser;
    }

}
