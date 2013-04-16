package ca.ulaval.glo4002.centralServer.user;

public class UserDirectoryLocator {

    public static UserDirectoryLocator instance;
    private UserDirectory userDirectory = new UserDirectory();

    public static synchronized UserDirectoryLocator getInstance() {
        if (instance == null) {
            System.out.println("crée le userDirectoryLocator");
            instance = new UserDirectoryLocator();
        }
        return instance;
    }

    public UserDirectory getUserDirectory() {
        return userDirectory;
    }

    public void deleteDirectory() {
        instance = null;
    }

}
