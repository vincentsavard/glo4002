package ca.ulaval.glo4002.communication;

import java.util.HashMap;

import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public abstract class CommunicationUnit {

    private JSONMessageEncoder messageEncoder;
    private POSTRequestSender requestSender;
    protected String resource;
    protected HashMap<String, String> resourceAttributes;
    protected ProtocolBuilder protocolBuilder;

    public CommunicationUnit() {
        messageEncoder = new JSONMessageEncoder();
        requestSender = new POSTRequestSender();
        protocolBuilder = new ProtocolBuilder();
    }

    public void send() {
        String messageToSend = messageEncoder.generateEncodedMessage(resourceAttributes);
        requestSender.sendPostRequest(resource, messageToSend);
    }

}
