package ca.ulaval.glo4002.communication;

import java.util.HashMap;

import ca.ulaval.glo4002.common.requestSender.GETRequestSender;
import ca.ulaval.glo4002.common.requestSender.POSTRequestSender;
import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class Communicator {

    private static final int CENTRAL_SERVER_PORT = 9001;

    public static enum TargetResource {
        FIRE, POLICE, REGISTRATION
    };

    protected int userID;
    protected JSONMessageEncoder messageEncoder = new JSONMessageEncoder();
    protected POSTRequestSender postRequestSender = new POSTRequestSender(CENTRAL_SERVER_PORT);
    protected GETRequestSender getRequestSender = new GETRequestSender(CENTRAL_SERVER_PORT);;

    public Communicator(int userID) {
        this.userID = userID;
    }

    protected Communicator() {

    }

    private String generateResourceURL(int userID, TargetResource targetResource) {
        return String.format("client/%d/%s", userID, targetResource.toString().toLowerCase());
    }

    public void sendMessageToCentralServer(TargetResource targetResource) {
        String resourceURL = generateResourceURL(userID, targetResource);
        getRequestSender.sendRequest(resourceURL);
    }

    public void sendMessageToCentralServer(HashMap<String, String> attributes, TargetResource targetResource) {
        String messageToSend = messageEncoder.generateEncodedMessage(attributes);
        String resourceURL = generateResourceURL(userID, targetResource);
        postRequestSender.sendRequest(resourceURL, messageToSend);
    }

}
