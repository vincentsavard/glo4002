package ca.ulaval.glo4002.communication;

import java.util.HashMap;

public class ProtocolBuilder {

    private static final String ADDRESS_KEY = "address";

    private HashMap<String, String> attributes;

    public ProtocolBuilder() {
        attributes = new HashMap<String, String>();
    }

    public HashMap<String, String> generate() {
        return attributes;
    }

    public void addClientAddress(final String address) {
        attributes.put(ADDRESS_KEY, address);
    }
}