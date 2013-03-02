package ca.ulaval.glo4002.common;

public class HTTPException extends RuntimeException {

    private static final long serialVersionUID = -8229468770910914564L;

    public HTTPException(final String message) {
        super(message);
    }
}
