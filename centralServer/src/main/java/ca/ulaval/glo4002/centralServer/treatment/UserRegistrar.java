package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public class UserRegistrar {

    private int userIdCounter = 0;
    private UsersDirectory usersDirectory;

    public UserRegistrar() {
        usersDirectory = UsersDirectory.getInstance();
    }

    // for test purpose only
    protected UserRegistrar(UsersDirectory usersDirectory) {
        this.usersDirectory = usersDirectory;
    }

    public int generateUniqueID() {
        userIdCounter++;
        return userIdCounter;
    }

    public void registerUser(int newUserId, String userInformation) {
        User newUser = createNewUser(newUserId, userInformation);
        usersDirectory.addUser(newUser);
    }

    private User createNewUser(int newUserId, String userInformation) {
        return new User(newUserId, userInformation);
    }
}
