package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;

public class UserRegistrarTest {

    private static final int A_USER_ID = 10;
    private static final String USER_INFORMATION = "some information";

    @Mock
    private Communicator communicator;

    @Mock
    private UserDirectory userDirectory;

    @InjectMocks
    private UserRegistrar userRegistrar;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void whenAUserIsRegisteredThenAUserIsAddedToTheUserDirectory() {
        userRegistrar.registerUser(A_USER_ID, USER_INFORMATION);
        verify(userDirectory).addUser(any(User.class));
    }

}
