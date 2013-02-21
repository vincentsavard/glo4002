package ca.ulaval.glo4002.communication;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ProtocolBuilderTest {
    private static final String AN_EMERGENCY_TYPE = "FIRE";
    private static final String AN_ADDRESS = "123 rue ville";
    private static final String EMERGENCY_TYPE_KEY = "emergencyType";
    private static final String ADDRESS_KEY = "address";
    private ProtocolBuilder protocolBuilder;
    private HashMap<String, String> attributes;

    @Before
    public void setUp() {
        protocolBuilder = new ProtocolBuilder();
        attributes = new HashMap<String, String>();
    }

    @Test
    public void hashMapIsEmptyWhenNoKeyIsAdded() {
        attributes = protocolBuilder.generate();

        assertTrue(attributes.isEmpty());
    }
    
    @Test
    public void canAddOneElement() {
        protocolBuilder.addEmergencyType(AN_EMERGENCY_TYPE);
        attributes = protocolBuilder.generate();
        
        assertFalse(attributes.isEmpty());
    }

    @Test
    public void canRetrieveElementsWhenElementsAreAdded() {
        protocolBuilder.addEmergencyType(AN_EMERGENCY_TYPE);
        protocolBuilder.addClientAddress(AN_ADDRESS);
        attributes = protocolBuilder.generate();
        
        assertEquals(attributes.get(EMERGENCY_TYPE_KEY), AN_EMERGENCY_TYPE);
        assertEquals(attributes.get(ADDRESS_KEY), AN_ADDRESS);
    }
}
