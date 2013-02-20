package ca.ulaval.glo4002.utilities;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class JSONMessageEncoderTest {

    @Test
    public void canGenerateEncodedMessageUsingJson() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Type", "ProblemType");
        map.put("Address", "theAddress");

        JSONMessageEncoder encoder = new JSONMessageEncoder();
        String encodedMessage = encoder.generateEncodedMessageUsingJSON(map);
        String expectedMessage = "{\"Type\":\"ProblemType\",\"Address\":\"theAddress\"}";

        assertEquals(expectedMessage, encodedMessage);
    }

}
