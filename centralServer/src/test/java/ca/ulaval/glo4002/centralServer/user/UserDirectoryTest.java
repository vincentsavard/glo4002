package ca.ulaval.glo4002.centralServer.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserDirectoryTest {

    private static final int AN_ID = 1;
    private static final int UNASSIGNED_ID = 4000;

    @Mock
    private User user;

    private UserDirectory userDirectory = new UserDirectory();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(AN_ID).when(user).getID();
    }

    @Test
    public void whenSearchingAnIDThatIsNotInTheDirectoryThenIDCanNotBeFound() {
        assertFalse(userDirectory.userExists(AN_ID));
    }

    @Test
    public void addedUserExistsInDirectory() {
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
    public void canGenerateANewUniqueID() {
        int firstID = userDirectory.generateNewId();
        int secondID = userDirectory.generateNewId();

        assertThat(firstID, is(not(secondID)));
    }

    @Test(expected = UserNotFoundException.class)
    public void tryingToObtainANotExistingUserThrowsAnException() throws UserNotFoundException {
        userDirectory.obtainUser(UNASSIGNED_ID);
    }

}
