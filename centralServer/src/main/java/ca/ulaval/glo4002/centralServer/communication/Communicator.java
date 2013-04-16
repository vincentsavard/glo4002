package ca.ulaval.glo4002.centralServer.communication;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.common.requestSender.HTTPRequestSender;

public class Communicator {

    private static final int EMERGENCY_SERVER_PORT = 9002;

    private HTTPRequestSender requestSender = new HTTPRequestSender(EMERGENCY_SERVER_PORT);
    private String resource;

    public static enum CommunicationType {
        FIRE, POLICE
    };

    public Communicator(CommunicationType communicationType) {
        resource = generateResourceURL(communicationType);
    }

    public void sendMessageToEmergencyServer(User obtainedUser) {
        String messageToSend = obtainedUser.getAddress();
        requestSender.sendPOSTRequest(resource, messageToSend);
    }

    private String generateResourceURL(CommunicationType communicationType) {
        return String.format("%s", communicationType.toString().toLowerCase());
    }

}
