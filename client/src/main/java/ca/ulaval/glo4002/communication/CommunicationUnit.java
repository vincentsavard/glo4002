package ca.ulaval.glo4002.communication;

import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public abstract class CommunicationUnit {

    protected JSONMessageEncoder messageEncoder;
    protected POSTRequestSender requestSender;
    protected String ressource;

    public CommunicationUnit() {
        messageEncoder = new JSONMessageEncoder();
        requestSender = new POSTRequestSender();
    }

    public abstract void send();

}
