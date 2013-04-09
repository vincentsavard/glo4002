package ca.ulaval.glo4002.centralServer.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Alarm {

    public enum AlarmType {
        FIRE, INTRUSION
    };

    private AlarmType alarmType;
    private Date date;

    public Alarm(AlarmType type, Date date) {
        this.alarmType = type;
        this.date = date;
    }

    public String getInformationForLogPurpose() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatedDate = dateFormat.format(date);

        String log = "Type=" + alarmType.toString() + ", date=" + formatedDate + "\n";

        return log;
    }

}
