package ca.ulaval.glo4002.domain.alarmSystem;

public class PINFormatForbiddenException extends RuntimeException {

    private static final long serialVersionUID = -1632579245623708591L;

    public PINFormatForbiddenException(String errorMessage) {
        super(errorMessage);
    }

}
