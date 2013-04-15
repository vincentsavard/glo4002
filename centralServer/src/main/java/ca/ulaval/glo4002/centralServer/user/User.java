package ca.ulaval.glo4002.centralServer.user;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    private int userID;
    private String userAddress;

    @XmlElementWrapper()
    @XmlElements({ @XmlElement(name = "alarms") })
    private ArrayList<Alarm> alarms = new ArrayList<Alarm>();

    protected User() {}

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

    public Alarm[] getGroups() {
        return alarms.toArray(new Alarm[alarms.size()]);
    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

}