package ca.ulaval.glo4002.client;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class MessageEncoder {

    public String generateEncodedMessage(HashMap<String, String> map) {
        return generateEncodedMessageUsingJSON(map);
    }

    @SuppressWarnings("unchecked")
    private String generateEncodedMessageUsingJSON(HashMap<String, String> map) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.putAll(map);

        return jsonObject.toJSONString();
    }
}
