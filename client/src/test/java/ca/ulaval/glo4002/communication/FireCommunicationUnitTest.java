package ca.ulaval.glo4002.communication;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FireCommunicationUnitTest {
    @Mock
    private ProtocolBuilder protocolBuilder;
    @InjectMocks
    private FireCommunicationUnit fireCommunicationUnit;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canBuildProtocolSuccessfully() {
        verify(fireCommunicationUnit.protocolBuilder).addEmergencyType("FIRE");
    }
}
