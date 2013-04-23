package ca.ulaval.glo4002.centralServer.core.communication;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.centralServer.domain.user.User;
import ca.ulaval.glo4002.common.core.requestSender.HTTPRequestSender;

public class Communicator {

    private static final String ADDRESS_KEY = "address";
    private static final String MESSAGE_KEY = "message";
    private static final String EMERGENCY_SERVER_URL = "http://localhost";
    private static final int EMERGENCY_SERVER_PORT = 9002;

    private HTTPRequestSender requestSender = new HTTPRequestSender(EMERGENCY_SERVER_URL, EMERGENCY_SERVER_PORT);

    public static enum CommunicationType {
        FIRE, POLICE
    };

    public void sendMessageToEmergencyServer(CommunicationType communicationType, User user) {
        String resource = generateResourceURL(communicationType);
        String messageToSend = user.getAddress();
        requestSender.sendPOSTRequest(resource, messageToSend);
    }

    public void sendMessageToEmergencyServer(CommunicationType communicationType, User user, String message) {
        String resource = generateResourceURL(communicationType);
        String userAddress = user.getAddress();
        String messageToSend = createMessageToSend(userAddress, message);
        requestSender.sendPOSTRequest(resource, messageToSend);
    }

    private String createMessageToSend(String userAddress, String message) {
        JSONObject messageToSend = new JSONObject();
        try {
            messageToSend.put(ADDRESS_KEY, userAddress);
            messageToSend.put(MESSAGE_KEY, message);
        } catch (JSONException e) {
            throw new JSONPutException(e.getMessage());
        }
        return messageToSend.toString();
    }

    private String generateResourceURL(CommunicationType communicationType) {
        return String.format("%s", communicationType.toString().toLowerCase());
    }

}
