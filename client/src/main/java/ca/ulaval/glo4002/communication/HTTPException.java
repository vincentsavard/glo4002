package ca.ulaval.glo4002.communication;

public class HTTPException extends RuntimeException {

    private static final long serialVersionUID = -8229468770910914564L;

    public HTTPException(String message) {
        super(message);
    }

}
