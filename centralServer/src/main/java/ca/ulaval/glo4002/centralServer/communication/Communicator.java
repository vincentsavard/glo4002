package ca.ulaval.glo4002.centralServer.communication;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.common.requestSender.POSTRequestSender;

public class Communicator {

    private static final int EMERGENCY_SERVER_PORT = 9002;
    private static final String ADDRESS_KEY = "address";
    private static final String MESSAGE_KEY = "message";

    private POSTRequestSender postRequestSender = new POSTRequestSender(EMERGENCY_SERVER_PORT);

    public static enum CommunicationType {
        FIRE, POLICE
    };

    public void sendMessageToEmergencyServer(CommunicationType communicationType, User obtainedUser) {
        String resource = generateResourceURL(communicationType);
        String messageToSend = obtainedUser.getAddress();
        postRequestSender.sendRequest(resource, messageToSend);
    }

    public void sendMessageToEmergencyServer(CommunicationType communicationType, User obtainedUser, String message) {
        String resource = generateResourceURL(communicationType);
        String userAddress = obtainedUser.getAddress();
        String messageToSend = createMessageToSend(userAddress, message);
        postRequestSender.sendRequest(resource, messageToSend);
    }

    private String createMessageToSend(String userAddress, String message) {
        JSONObject messageToSend = new JSONObject();
        try {
            messageToSend.put(ADDRESS_KEY, userAddress);
            messageToSend.put(MESSAGE_KEY, message);
        } catch (JSONException e) {
            throw new JsonPutException(e.getMessage());
        }
        return messageToSend.toString();
    }

    private String generateResourceURL(CommunicationType communicationType) {
        return String.format("%s", communicationType.toString().toLowerCase());
    }

}
