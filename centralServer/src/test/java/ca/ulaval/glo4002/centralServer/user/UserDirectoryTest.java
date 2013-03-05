package ca.ulaval.glo4002.centralServer.user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserDirectoryTest {

    private static final int AN_ID = 1;
    private static final int UNASSIGNED_ID = 4000;

    @Mock
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(AN_ID).when(user).getID();
    }

    @After
    public void tearDown() {
        UserDirectory.load(null);
    }

    @Test
    public void whenSearchingAnIDThatIsNotInTheDirectoryThenIDCanNotBeFound() {
        assertFalse(UserDirectory.getInstance().userExists(AN_ID));
    }

    @Test
    public void addedUserExistsInDirectory() {
        UserDirectory.getInstance().addUser(user);
        assertTrue(UserDirectory.getInstance().userExists(AN_ID));
    }

    @Test
    public void existingUserCanBeReturned() throws UserNotFoundException {
        UserDirectory.getInstance().addUser(user);
        User receivedUser = UserDirectory.getInstance().obtainUser(AN_ID);
        assertEquals(user.getID(), receivedUser.getID());
    }

    @Test
    public void canGenerateANewUniqueID() {
        int firstID = UserDirectory.getInstance().generateNewId();
        int secondID = UserDirectory.getInstance().generateNewId();

        assertFalse(firstID == secondID);
    }

    @Test(expected = UserNotFoundException.class)
    public void tryingToObtainANotExistingUserThrowsAnException() throws UserNotFoundException {
        UserDirectory.getInstance().obtainUser(UNASSIGNED_ID);
    }

}
