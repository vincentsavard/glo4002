package ca.ulaval.glo4002.centralServer.treatment;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UsersDirectory;

public class UserRegistrarTest {

    private static final int A_USER_ID = 10;
    private static final String USER_INFORMATION = "some information";

    private UserRegistrar userRegistrar;

    @Mock
    private CommunicationUnit communicationUnit;

    @Mock
    private UsersDirectory usersDirectory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usersDirectory = mock(UsersDirectory.class);
        userRegistrar = new UserRegistrar(usersDirectory);
    }

    @Test
    public void eachIDGeneratedIsAnIncrementOfTheLastGeneratedID() {
        int firstGeneratedId = userRegistrar.generateUniqueID();
        int secondGeneratedID = userRegistrar.generateUniqueID();
        assertTrue((secondGeneratedID - 1) == firstGeneratedId);
    }

    @Test
    public void whenAUserIsRegistredThenAUserIsAddedToTheUsersDirectory() {
        userRegistrar.registerUser(A_USER_ID, USER_INFORMATION);
        verify(usersDirectory).addUser(any(User.class));
    }
}
