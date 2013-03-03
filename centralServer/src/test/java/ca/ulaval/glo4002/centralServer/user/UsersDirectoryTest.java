package ca.ulaval.glo4002.centralServer.user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UsersDirectoryTest {

    private static final int AN_ID = 1;

    private UsersDirectory userDirectory;

    @Mock
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userDirectory = new UsersDirectory();
        doReturn(AN_ID).when(user).getID();
    }

    @Test
    public void searchingAnIDThatIsNotInTheDirectoryCanNotBeFound() {
        assertFalse(userDirectory.userExists(AN_ID));
    }

    @Test
    public void addedUserExistInDirectory() {
        userDirectory.addUser(user);
        assertTrue(userDirectory.userExists(AN_ID));
    }

    @Test
    public void existingUserCanBeReturned() throws UserNotFoundException {
        userDirectory.addUser(user);
        User receivedUser = userDirectory.obtainUser(AN_ID);
        assertEquals(user.getID(), receivedUser.getID());
    }
    
    @Test
    public void addingAUserIncreasesNumberOfUsers() {
        userDirectory.addUser(user);
        assertEquals(userDirectory.getNumberOfUsers(), 1);
    }

    @Test(expected = UserNotFoundException.class)
    public void tryingToObtainANotExistingUserThrowsAnException() throws UserNotFoundException {
        userDirectory.obtainUser(AN_ID);
    }
}
