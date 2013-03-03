package ca.ulaval.glo4002.communication;

import java.util.HashMap;

import ca.ulaval.glo4002.common.GETRequestSender;
import ca.ulaval.glo4002.common.POSTRequestSender;
import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class CommunicationUnit {

    public static enum CommunicationType {
        FIRE, INTRUSION, REGISTRATION
    };

    protected JSONMessageEncoder messageEncoder = new JSONMessageEncoder();

    protected String resource;

    protected POSTRequestSender postRequestSender = new POSTRequestSender();
    protected GETRequestSender getRequestSender = new GETRequestSender();
    ;

    public CommunicationUnit(final int userID, final CommunicationType communicationType) {
        resource = generateResourceURL(userID, communicationType);
    }

    protected CommunicationUnit() {
        resource = CommunicationType.REGISTRATION.toString();
    }

    private String generateResourceURL(final int userID, final CommunicationType communicationType) {
        return String.format("%s/%d", communicationType.toString().toLowerCase(), userID);
    }

    public void send() {
        getRequestSender.sendRequest(resource);
    }

    public void send(final HashMap<String, String> attributes) {
        String messageToSend = messageEncoder.generateEncodedMessage(attributes);
        postRequestSender.sendRequest(resource, messageToSend);
    }
}
