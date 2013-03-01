package ca.ulaval.glo4002.utilities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class DelayTimerTest {

    private static final int A_DELAY = 2;

    private DelayTimer delayManager;

    @Before
    public void setUp() {
        delayManager = new DelayTimer(mock(DelayTimerDelegate.class));
    }

    @Test
    public void canStartDelay() {
        delayManager.startDelay(A_DELAY);
        assertTrue(delayManager.isRunning());
    }

    @Test
    public void canDestroyTimer() {
        delayManager.startDelay(A_DELAY);
        delayManager.cancelDelay();

        assertFalse(delayManager.isRunning());
    }
}
