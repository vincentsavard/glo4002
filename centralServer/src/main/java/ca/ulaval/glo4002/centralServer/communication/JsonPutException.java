package ca.ulaval.glo4002.centralServer.communication;

public class JsonPutException extends RuntimeException {

    private static final long serialVersionUID = 326703006700445899L;

    public JsonPutException(String errorMessage) {
        super(errorMessage);
    }

}
