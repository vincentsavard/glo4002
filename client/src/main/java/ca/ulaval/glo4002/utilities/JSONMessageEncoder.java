package ca.ulaval.glo4002.utilities;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class JSONMessageEncoder {

    @SuppressWarnings("unchecked")
    public String generateEncodedMessage(HashMap<String, String> attributes) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(attributes);
        return jsonObject.toJSONString();
    }

}
