package ca.ulaval.glo4002.centralServer.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    private int userID;
    private String userAddress;

    private List<Alarm> alarms = Collections.synchronizedList(new ArrayList<Alarm>());

    public User(int userID, String address) {
        this.userID = userID;
        this.userAddress = address;
    }

    public boolean isSameID(int ID) {
        return userID == ID;
    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    // For POJO object mapping
    protected User() {}

    // For POJO object mapping
    public String getAddress() {
        return userAddress;
    }

    // For POJO object mapping
    public Alarm[] getAlarms() {
        return alarms.toArray(new Alarm[alarms.size()]);
    }

}
