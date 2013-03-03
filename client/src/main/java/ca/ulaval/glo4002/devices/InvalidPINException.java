package ca.ulaval.glo4002.devices;

public class InvalidPINException extends RuntimeException {

    private static final long serialVersionUID = -1247342906520356085L;

    public InvalidPINException(String errorMessage) {
        super(errorMessage);
    }

}
