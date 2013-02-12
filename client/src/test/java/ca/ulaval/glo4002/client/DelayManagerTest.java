package ca.ulaval.glo4002.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class DelayManagerTest {

    private final int AN_IDENTIFIER = 2;
    private final int A_DELAY = 2;
    private DelayTimer delayManager;

    @Before
    public void setUp() {
        delayManager = new DelayTimer(mock(DelayTimerDelegate.class));
    }

    @Test
    public void canStartDelay() {
        delayManager.startDelay(A_DELAY, AN_IDENTIFIER);
        assertTrue(delayManager.isRunning());
    }

    @Test
    public void canDestroyTimer() {
        delayManager.startDelay(A_DELAY, AN_IDENTIFIER);
        delayManager.cancelDelay();

        assertFalse(delayManager.isRunning());
    }
}
