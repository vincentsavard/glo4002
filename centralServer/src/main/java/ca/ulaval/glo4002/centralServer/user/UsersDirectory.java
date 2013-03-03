package ca.ulaval.glo4002.centralServer.user;

import java.util.ArrayList;
import java.util.List;

public class UsersDirectory {

    private List<User> userList;
    private static UsersDirectory instance;

    public UsersDirectory() {
        this.userList = new ArrayList<User>();
    }

    public boolean userExists(final int anID) {
        boolean answer = false;

        for (User user : userList) {
            if (user.getID() == anID) {
                answer = true;
            }
        }
        return answer;
    }

    public void addUser(final User user) {
        userList.add(user);
    }

    public User obtainUser(final int anID) throws UserNotFoundException {
        User userToBeFound = null;

        for (User user : userList) {
            if (user.getID() == anID) {
                userToBeFound = user;
            }
        }

        if (userToBeFound == null) {
            throw new UserNotFoundException(
                    "This ID was not found in the UsersDirectory when trying to obtain the corresponding user");
        }

        return userToBeFound;
    }

    public int getNumberOfUsers() {
        return userList.size();
    }

    public static UsersDirectory getInstance() {
        if (instance == null) {
            instance = new UsersDirectory();
        }
        return instance;
    }
}
