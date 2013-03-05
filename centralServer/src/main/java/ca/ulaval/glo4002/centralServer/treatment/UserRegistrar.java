package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;

public class UserRegistrar {

    public int generateUserID() {
        int newUserId = UserDirectory.getInstance().generateNewId();
        return newUserId;
    }

    public void registerUser(int newUserId, String userInformation) {
        User newUser = createNewUser(newUserId, userInformation);
        UserDirectory.getInstance().addUser(newUser);
    }

    private User createNewUser(int newUserId, String userInformation) {
        User newUser = new User(newUserId, userInformation);
        return newUser;
    }

}
