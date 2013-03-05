package ca.ulaval.glo4002.centralServer.user;

public class User {

    private int userID;
    private String userAddress;

    public User(int userID, String address) {
        this.userID = userID;
        this.userAddress = address;
    }

    public int getID() {
        return userID;
    }

    public String getAddress() {
        return userAddress;
    }

}
