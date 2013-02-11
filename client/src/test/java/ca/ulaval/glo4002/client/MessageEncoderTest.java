package ca.ulaval.glo4002.client;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class MessageEncoderTest {

    @Test
    public void canGenerateEncodedMessageUsingJson() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Type", "ProblemType");
        map.put("Address", "theAddress");

        MessageEncoder encoder = new MessageEncoder();
        String encodedMessage = encoder.generateEncodedMessage(map);
        String expectedMessage = "{\"Type\":\"ProblemType\",\"Address\":\"theAddress\"}";

        assertEquals(expectedMessage, encodedMessage);
    }

}
