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

<<<<<<< HEAD
=======
    public void addUser(User user) {
        users.add(user);
    }

>>>>>>> d5a582e60bfdc29f41c5f2a3c4a5056bad0831ad
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

    public void registerUser(int newUserID, String userInformation) {
        User newUser = new User(newUserID, userInformation);
        userList.add(newUser);
    }

}
