package ca.ulaval.glo4002.centralServer.domain.user;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ca.ulaval.glo4002.centralServer.core.communication.Communicator.CommunicationType;

@XmlRootElement(name = "alarm")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alarm {

    private CommunicationType communicationType;
    private Date date;

    public Alarm(CommunicationType type, Date date) {
        this.communicationType = type;
        this.date = date;
    }

    // For POJO object mapping
    protected Alarm() {}

    // For POJO object mapping
    public CommunicationType getAlarmType() {
        return communicationType;
    }

    // For POJO object mapping
    public Date getDate() {
        return date;
    }

}
