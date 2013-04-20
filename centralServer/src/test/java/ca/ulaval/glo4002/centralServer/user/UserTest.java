package ca.ulaval.glo4002.centralServer.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
    public void whenAskingIfItIsTheSameIdWithTheSameIdThenItReturnTrue() {
        assertTrue(user.isSameID(USER_ID));
    }

    @Test
    public void whenAskingIfItIsTheSameIdWithAnotherIdThenItReturnFalse() {
        assertFalse(user.isSameID(NOT_THE_USER_ID));
    }

}
