package ca.ulaval.glo4002.centralServer.domain.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.domain.user.User;

public class UserTest {

    private static String USER_ADDRESS = "123 rue Godo";
    private static int USER_ID = 2;
    private static int NOT_THE_USER_ID = 10;

    private User user;

    @Before
    public void setUp() {
        user = new User(USER_ID, USER_ADDRESS);
    }

    @Test
    public void whenAskingIfItIsTheSameIDWithTheSameIDThenItReturnTrue() {
        assertTrue(user.isSameID(USER_ID));
    }

    @Test
    public void whenAskingIfItIsTheSameIDWithAnotherIDThenItReturnFalse() {
        assertFalse(user.isSameID(NOT_THE_USER_ID));
    }

}
