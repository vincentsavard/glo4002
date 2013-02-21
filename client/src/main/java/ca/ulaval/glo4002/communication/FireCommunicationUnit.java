package ca.ulaval.glo4002.communication;

import java.util.HashMap;

public class FireCommunicationUnit extends CommunicationUnit {

    private static final String EMERGENCY_TYPE = "fire";
    private static final String ADDRESS = "123 rue ville";

    public FireCommunicationUnit() {
        super();
        resource = EMERGENCY_TYPE;
        resourceAttributes = generateResourceAttributes();
    }
    
    private HashMap<String, String> generateResourceAttributes() {
        protocolBuilder.addEmergencyType(EMERGENCY_TYPE);
        protocolBuilder.addClientAddress(ADDRESS);
        return protocolBuilder.generate();
    }

}
