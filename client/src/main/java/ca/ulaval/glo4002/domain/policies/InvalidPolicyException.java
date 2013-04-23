package ca.ulaval.glo4002.domain.policies;

public class InvalidPolicyException extends RuntimeException {

    private static final long serialVersionUID = -8266249108209068189L;

    public InvalidPolicyException(String errorMessage) {
        super(errorMessage);
    }

}
