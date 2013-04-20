package ca.ulaval.glo4002.devices;

public class RecentlyUsedPINException extends RuntimeException {

    private static final long serialVersionUID = 1206515415785033418L;

    public RecentlyUsedPINException(String errorMessage) {
        super(errorMessage);
    }

}
