package ca.ulaval.glo4002.centralServer.user;

import java.util.ArrayList;

public class User {

    private int userID;
    private String userAddress;
    private ArrayList<Alarm> alarms = new ArrayList<Alarm>();

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

    public String createLogForAllAlarms() {
        String log = userID + "\nAddresse: " + userAddress + "\nAlarms' list: \n";
        if (alarms.isEmpty()) {
            log += "There is no alarms for this user.";
        } else {
            log += formatAlarms();
        }
        return log;
    }

    private String formatAlarms() {
        String formatedAlarms = "";
        for (int i = 0; i < alarms.size(); i++) {
            formatedAlarms += alarms.get(i).getInformationForLogPurpose();
        }
        return formatedAlarms;
    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

}
