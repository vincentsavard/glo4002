package ca.ulaval.glo4002.centralServer.user;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -600375393851604682L;

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
