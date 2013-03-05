package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.CommunicationUnit;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;

public class UserRegistrarTest {

    private static final int A_USER_ID = 10;
    private static final String USER_INFORMATION = "some information";

    @Mock
    private CommunicationUnit communicationUnit;

    @InjectMocks
    private UserRegistrar userRegistrar;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UserDirectory mockedUserDirectory = mock(UserDirectory.class);
        UserDirectory.load(mockedUserDirectory);
    }

    @After
    public void tearDown() {
        UserDirectory.load(null);
    }

    @Test
    public void whenAUserIsRegisteredThenAUserIsAddedToTheUserDirectory() {
        userRegistrar.registerUser(A_USER_ID, USER_INFORMATION);
        verify(UserDirectory.getInstance()).addUser(any(User.class));
    }

}
