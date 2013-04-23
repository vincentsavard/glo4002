package ca.ulaval.glo4002.centralServer.core.communication;

public class JSONPutException extends RuntimeException {

    private static final long serialVersionUID = 326703006700445899L;

    public JSONPutException(String errorMessage) {
        super(errorMessage);
    }

}
