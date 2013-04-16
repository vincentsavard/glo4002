package ca.ulaval.glo4002.centralServer.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;

public class Alarm {

    private CommunicationType communicationType;
    private Date date;

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

}
