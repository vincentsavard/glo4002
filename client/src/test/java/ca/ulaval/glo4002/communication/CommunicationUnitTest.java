package ca.ulaval.glo4002.communication;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class CommunicationUnitTest {
    @Test
    public void test() {
        CommunicationUnit cu = mock(CommunicationUnit.class, Mockito.CALLS_REAL_METHODS);
    }
}
