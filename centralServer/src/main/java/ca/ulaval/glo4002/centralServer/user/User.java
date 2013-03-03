package ca.ulaval.glo4002.centralServer.user;

public class User {

    private int userID;
    private String userAddress;

    public User(final int anID, final String anAddress) {
        this.userID = anID;
        this.userAddress = anAddress;
    }

    public int getID() {
        return userID;
    }

    public String getAddress() {
        return userAddress;
    }

}
