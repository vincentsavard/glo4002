package ca.ulaval.glo4002.context;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;
import ca.ulaval.glo4002.domain.devices.Detector;
import ca.ulaval.glo4002.domain.policies.FirePolicy;
import ca.ulaval.glo4002.domain.policies.IntrusionPolicy;
import ca.ulaval.glo4002.domain.policies.MainDoorIntrusionPolicy;
import ca.ulaval.glo4002.domain.policies.Policy;

public class HouseContext {

    private Communicator communicator;
    private AlarmSystem alarmSystem = new AlarmSystem();

    private List<Detector> detectors = new ArrayList<Detector>();

    public HouseContext(String houseAddress) {
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

    // For test purposes only
    protected int getNumberOfDetectors() {
        return detectors.size();
    }

    // For test purposes only
    protected HouseContext(AlarmSystem alarmSystem, Communicator communicator) {
        this.alarmSystem = alarmSystem;
        this.communicator = communicator;
    }

}
