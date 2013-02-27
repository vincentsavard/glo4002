package ca.ulaval.glo4002.communication;

import java.util.HashMap;

import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class CommunicationUnit {
    public static enum CommunicationType {
        FIRE, INTRUSION, REGISTRATION
    };

    protected JSONMessageEncoder messageEncoder = new JSONMessageEncoder();
    protected POSTRequestSender postRequestSender = new POSTRequestSender();;
    protected String resource;

    public CommunicationUnit(int userID, CommunicationType communicationType) {
        resource = generateResourceURL(userID, communicationType);
    }

    protected CommunicationUnit() {
        resource = CommunicationType.REGISTRATION.toString();
    }

    private String generateResourceURL(int userID,
            CommunicationType communicationType) {
        return String.format("%s/%d", communicationType.toString(), userID);
    }

    public void send() {
        send(new HashMap<String, String>());
    }

    public void send(HashMap<String, String> attributes) {
        String messageToSend = messageEncoder
                .generateEncodedMessage(attributes);
        postRequestSender.sendPostRequest(resource, messageToSend);
    }
}
