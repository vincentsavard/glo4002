package ca.ulaval.glo4002.centralServer.domain.user;

public class UserDirectoryLocator {

    public static UserDirectoryLocator instance;
    private UserDirectory userDirectory = new UserDirectory();

    public static synchronized UserDirectoryLocator getInstance() {
        if (instance == null) {
            instance = new UserDirectoryLocator();
        }
        return instance;
    }

    public UserDirectory getUserDirectory() {
        return userDirectory;
    }

    // For test purposes only
    public void deleteDirectory() {
        instance = null;
    }

}
