package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public class UserRegistrar {

    private UsersDirectory usersDirectory;

    public UserRegistrar() {
        usersDirectory = UsersDirectory.getInstance();
    }

    public int generateUserID() {
        int newUserId = usersDirectory.generateNewId();
        return newUserId;
    }

    public void registerUser(int newUserId, String userInformation) {
        User newUser = createNewUser(newUserId, userInformation);
        usersDirectory.addUser(newUser);
    }

    private User createNewUser(int newUserId, String userInformation) {
        return new User(newUserId, userInformation);
    }

    // for test purpose only
    protected UserRegistrar(UsersDirectory usersDirectory) {
        this.usersDirectory = usersDirectory;
    }
}
