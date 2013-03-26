package ca.ulaval.glo4002.communication;

import java.util.HashMap;


public class Registrar {

    private static final String ADDRESS_KEY = "address";
    
    public int requestRegistrationToCentralServer(String address) {
        RegistrationCommunicator registrationCommunicator = new RegistrationCommunicator();
        HashMap<String, String> attributes = new HashMap<String, String>();
        int userID;
        
        attributes.put(ADDRESS_KEY, address);
        registrationCommunicator.sendRegistrationRequest(attributes);
        userID = registrationCommunicator.retrieveUserID();
        
        return userID;
    }
    
}
