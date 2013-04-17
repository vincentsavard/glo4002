package ca.ulaval.glo4002.centralServer.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;

@XmlRootElement(name = "alarm")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alarm {

    private CommunicationType communicationType;
    private Date date;

    protected Alarm() {}

    public Alarm(CommunicationType type, Date date) {
        this.communicationType = type;
        this.date = date;
    }

    public String getInformationForLogPurpose() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatedDate = dateFormat.format(date);
        String log = "Type=" + communicationType.toString() + ", date=" + formatedDate + "\n";
        return log;
    }

    public CommunicationType getAlarmType() {
        return communicationType;
    }

    public Date getDate() {
        return date;
    }

}
