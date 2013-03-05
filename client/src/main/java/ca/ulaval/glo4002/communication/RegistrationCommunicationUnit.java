package ca.ulaval.glo4002.communication;

import java.util.HashMap;

public class RegistrationCommunicationUnit extends CommunicationUnit {

    private static final String RESOURCE = "register/";

    private String response;

    public RegistrationCommunicationUnit() {
        resource = RESOURCE;
    }

    public void sendRegistrationRequest(HashMap<String, String> attributes) {
        String message = messageEncoder.generateEncodedMessage(attributes);
        response = postRequestSender.sendRequest(resource, message);
    }

    public int retrieveUserID() {
        int userID = Integer.parseInt(response);
        return userID;
    }

}
