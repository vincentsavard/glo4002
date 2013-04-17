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
    private static final String USER_INFORMATION = "some information";

    @Mock
    private User user;

    private UserDirectory userDirectory = new UserDirectory();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(true).when(user).isSameID(AN_ID);
    }

    @Test
    public void whenSearchingAnIDThatIsNotInTheDirectoryThenIDCanNotBeFound() {
        assertFalse(userDirectory.userExists(AN_ID));
    }

    @Test
    public void registeredUserExistsInDirectory() {
        userDirectory.registerUser(AN_ID, USER_INFORMATION);
        assertTrue(userDirectory.userExists(AN_ID));
    }

    @Test
    public void whenAUserIsRegisteredThenAUserIsAddedToTheUserDirectory() {
        userDirectory.registerUser(AN_ID, USER_INFORMATION);
        User receivedUser = userDirectory.obtainUser(AN_ID);
        assertTrue(user.isSameID(AN_ID));
        assertTrue(receivedUser.isSameID(AN_ID));
    }

    @Test
    public void canGenerateANewUniqueID() {
        int firstID = userDirectory.generateNewID();
        int secondID = userDirectory.generateNewID();

        assertThat(firstID, is(not(secondID)));
    }

    @Test(expected = UserNotFoundException.class)
    public void tryingToObtainANotExistingUserThrowsAnException() throws UserNotFoundException {
        userDirectory.obtainUser(UNASSIGNED_ID);
    }

}
