package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public class UserRegistrar {

    private int userIdCounter = 0;
    private UsersDirectory usersDirectory;

    public UserRegistrar() {
        usersDirectory = UsersDirectory.getInstance();
    }

    public int generateUniqueID() {
        userIdCounter++;
        return userIdCounter;
    }

    public void registerUser(final int newUserId, final String userInformation) {
        User newUser = createNewUser(newUserId, userInformation);
        usersDirectory.addUser(newUser);
    }

    private User createNewUser(final int newUserId, final String userInformation) {
        return new User(newUserId, userInformation);
    }

    // for test purpose only
    protected UserRegistrar(final UsersDirectory usersDirectory) {
        this.usersDirectory = usersDirectory;
    }
}
