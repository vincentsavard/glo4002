package ca.ulaval.glo4002.core.communication;

import ca.ulaval.glo4002.common.core.requestSender.HTTPRequestSender;

public class Communicator {

    private static final String CENTRAL_SERVER_URL = "http://localhost";
    private static final String REGISTER_URL = "register/";
    private static final int CENTRAL_SERVER_PORT = 9001;

    public static enum TargetResource {
        FIRE, POLICE, REGISTRATION
    };

    private int userID;
    private HTTPRequestSender requestSender = new HTTPRequestSender(CENTRAL_SERVER_URL, CENTRAL_SERVER_PORT);

    public Communicator(String houseAddress) {
        requestUserIDFromCentralServer(houseAddress);
    }

    private void requestUserIDFromCentralServer(String houseAddress) {
        String response = sendMessageToCentralServer(TargetResource.REGISTRATION, houseAddress);
        userID = Integer.parseInt(response);
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

    public String sendMessageToCentralServer(TargetResource targetResource, String messageToSend) {
        String resourceURL = generateResourceURL(targetResource);
        String response = requestSender.sendPOSTRequest(resourceURL, messageToSend);
        return response;
    }

    // For test purposes only
    protected Communicator(String houseAddress, HTTPRequestSender requestSender) {
        this.requestSender = requestSender;

        requestUserIDFromCentralServer(houseAddress);
    }

}
