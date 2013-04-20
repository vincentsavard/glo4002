package ca.ulaval.glo4002.systemContext;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.devices.Detector;
import ca.ulaval.glo4002.policies.FirePolicy;
import ca.ulaval.glo4002.policies.IntrusionPolicy;
import ca.ulaval.glo4002.policies.MainDoorIntrusionPolicy;
import ca.ulaval.glo4002.policies.Policy;

public class SystemContext {

    private Communicator communicator;
    private AlarmSystem alarmSystem = new AlarmSystem();

    private List<Detector> detectors = new ArrayList<Detector>();

    public SystemContext(String houseAddress) {
        communicator = new Communicator(houseAddress);
    }

    public void createMainDoorDetector(int zone) {
        Policy mainDoorIntrusionPolicy = new MainDoorIntrusionPolicy(alarmSystem, communicator);
        Detector mainDoorDetector = new Detector(mainDoorIntrusionPolicy, zone);
        detectors.add(mainDoorDetector);
    }

    public void createIntrusionDetector(int zone) {
        Policy intrusionPolicy = new IntrusionPolicy(alarmSystem, communicator);
        Detector intrusionDetector = new Detector(intrusionPolicy, zone);
        detectors.add(intrusionDetector);
    }

    public void createFireDetector(int zone) {
        Policy firePolicy = new FirePolicy(alarmSystem, communicator);
        Detector fireDetector = new Detector(firePolicy, zone);
        detectors.add(fireDetector);
    }

    // For test purpose only
    protected int getNumberOfDetectors() {
        return detectors.size();
    }

    // For test purpose only
    protected SystemContext(AlarmSystem alarmSystem, Communicator communicator) {
        this.alarmSystem = alarmSystem;
        this.communicator = communicator;
    }

}
