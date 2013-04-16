package ca.ulaval.glo4002.centralServer.user;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alarm")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alarm {

    public enum AlarmType {
        FIRE, INTRUSION
    };

    protected Alarm() {}

    private AlarmType alarmType;
    private Date date;

    public Alarm(AlarmType type, Date date) {
        this.alarmType = type;
        this.date = date;
    }

    public AlarmType getAlarmType() {
        return alarmType;
    }

    public Date getDate() {
        return date;
    }

}
