package ca.ulaval.glo4002.centralServer.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDirectory {

    private List<User> users = Collections.synchronizedList(new ArrayList<User>());
    private int lastIdGenerated = 0;

    public boolean userExists(int userID) {
        for (User user : users) {
            if (user.isSameID(userID)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User obtainUser(int userID) throws UserNotFoundException {
        for (User user : users) {
            if (user.isSameID(userID)) {
                return user;
            }
        }
        throw new UserNotFoundException("This ID was not found in the UserDirectory "
                                        + "when trying to obtain the corresponding user");
    }

    public int generateNewID() {
        lastIdGenerated++;
        return lastIdGenerated;
    }

}
