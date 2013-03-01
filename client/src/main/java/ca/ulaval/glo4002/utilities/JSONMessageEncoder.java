package ca.ulaval.glo4002.utilities;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class JSONMessageEncoder {

    @SuppressWarnings("unchecked")
    public String generateEncodedMessage(final HashMap<String, String> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        return jsonObject.toJSONString();
    }
}
