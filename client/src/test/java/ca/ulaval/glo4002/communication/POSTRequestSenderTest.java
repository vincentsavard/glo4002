package ca.ulaval.glo4002.communication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class POSTRequestSenderTest {
    POSTRequestSender postRequestSender;

    @Before
    public void initHomeConnectionHandler() {
        postRequestSender = new POSTRequestSender();
    }

    @Test
    public void homeHasBeenInitializedCorrectly() {
        assertTrue(postRequestSender.getWebResource() != null);
    }

}
