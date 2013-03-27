package ca.ulaval.glo4002.communication;

import java.util.HashMap;

public class Registrar {

    private static final String ADDRESS_KEY = "address";

    public int requestUserIDFromCentralServer(String houseAddress) {
        RegistrationCommunicator registrationCommunicator = new RegistrationCommunicator();
        HashMap<String, String> attributes = new HashMap<String, String>();
        int userID;

        attributes.put(ADDRESS_KEY, houseAddress);
        registrationCommunicator.sendRegistrationRequest(attributes);
        userID = registrationCommunicator.retrieveUserID();

        return userID;
    }

}
