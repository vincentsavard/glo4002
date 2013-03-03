package ca.ulaval.glo4002.devices;

public class BadStateException extends RuntimeException {

    private static final long serialVersionUID = 5324955431686236622L;

    public BadStateException(String errorMessage) {
        super(errorMessage);
    }

}
