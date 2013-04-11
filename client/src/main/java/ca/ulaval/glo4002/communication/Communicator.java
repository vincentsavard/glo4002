package ca.ulaval.glo4002.communication;

import java.util.HashMap;

import ca.ulaval.glo4002.common.requestSender.HTTPRequestSender;
import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class Communicator {

    private static final int CENTRAL_SERVER_PORT = 9001;
    private static final String ADDRESS_KEY = "address";
    private static final String REGISTER_URL = "register/";

    public static enum TargetResource {
        FIRE, POLICE, REGISTRATION
    };

    private int userID;
    private JSONMessageEncoder messageEncoder = new JSONMessageEncoder();
    private HTTPRequestSender requestSender = new HTTPRequestSender(CENTRAL_SERVER_PORT);

    public Communicator(String houseAddress) {
        requestUserIDFromCentralServer(houseAddress);
    }

    private void requestUserIDFromCentralServer(String houseAddress) {
        HashMap<String, String> attributes = new HashMap<String, String>();

        attributes.put(ADDRESS_KEY, houseAddress);
        sendRegisterRequestToCentralServer(attributes);
    }

    // This method is protected because it is used in the tests
    protected String generateResourceURL(TargetResource targetResource) {
        if (targetResource.equals(TargetResource.REGISTRATION)) {
            return REGISTER_URL;
        } else {
            return String.format("client/%d/%s", userID, targetResource.toString().toLowerCase());
        }
    }

    public void sendMessageToCentralServer(TargetResource targetResource) {
        String resourceURL = generateResourceURL(targetResource);
        requestSender.sendPOSTRequest(resourceURL);
    }

    public String sendMessageToCentralServer(HashMap<String, String> attributes, TargetResource targetResource) {
        String messageToSend = messageEncoder.generateEncodedMessage(attributes);
        String resourceURL = generateResourceURL(targetResource);
        String response = requestSender.sendPOSTRequest(resourceURL, messageToSend);
        return response;
    }

    private void sendRegisterRequestToCentralServer(HashMap<String, String> attributes) {
        String response = sendMessageToCentralServer(attributes, TargetResource.REGISTRATION);
        userID = Integer.parseInt(response);
    }

    // For test purposes only
    protected Communicator(String houseAddress, HTTPRequestSender requestSender, JSONMessageEncoder messageEncoder) {
        this.requestSender = requestSender;
        this.messageEncoder = messageEncoder;
        requestUserIDFromCentralServer(houseAddress);
    }

}
