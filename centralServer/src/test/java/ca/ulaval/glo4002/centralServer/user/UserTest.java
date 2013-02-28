package ca.ulaval.glo4002.centralServer.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private static final int AN_ID = 1;
    private static final int EXPECTED_ID = 1;
    private static final String EXPECTED_ADRESS = "An Adress";
    private static final String AN_ADRESS = "An Adress";
    private User user;

    @Before
    public void setUp() {
        user = new User(AN_ID, AN_ADRESS);
    }

    @Test
    public void initializedUserHasAnID() {
        assertEquals(user.getID(), EXPECTED_ID);
    }

    @Test
    public void intializedUserHasAnAdress() {
        assertEquals(user.getAdress(), EXPECTED_ADRESS);
    }
}
