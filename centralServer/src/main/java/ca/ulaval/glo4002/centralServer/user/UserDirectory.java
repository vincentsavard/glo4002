package ca.ulaval.glo4002.centralServer.user;

import java.util.ArrayList;
import java.util.List;

public class UserDirectory {

    private List<User> userList = new ArrayList<User>();
    private int lastIdGenerated = 0;

    public boolean userExists(int userID) {
        boolean answer = false;

        for (User user : userList) {
            if (user.getID() == userID) {
                answer = true;
            }
        }
        return answer;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User obtainUser(int userID) throws UserNotFoundException {
        User userToBeFound = null;

        for (User user : userList) {
            if (user.getID() == userID) {
                userToBeFound = user;
            }
        }
        if (userToBeFound == null) {
            throw new UserNotFoundException("This ID was not found in the UserDirectory "
                                            + "when trying to obtain the corresponding user");
        }
        return userToBeFound;
    }

    public int generateNewID() {
        lastIdGenerated++;
        return lastIdGenerated;
    }

}
