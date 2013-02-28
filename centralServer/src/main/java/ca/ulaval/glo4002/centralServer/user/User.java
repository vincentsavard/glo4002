package ca.ulaval.glo4002.centralServer.user;

public class User {

	private int userID;
	private String userAdress;

	public User(int anID, String anAdress) {
		this.userID = anID;
		this.userAdress = anAdress;
	}

	public int getID() {
	    return userID;
    }

	public Object getAdress() {
	    return userAdress;
    }

}
