package ca.ulaval.glo4002.core.timer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.core.timer.DelayTimer;
import ca.ulaval.glo4002.core.timer.DelayTimerDelegate;

public class DelayTimerTest {

    private static final int A_DELAY = 2;

    private DelayTimer delayManager;

    @Before
    public void setUp() {
        DelayTimerDelegate mockedDelayTimerDelegate = mock(DelayTimerDelegate.class);
        delayManager = new DelayTimer(mockedDelayTimerDelegate);
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

    @Test
    public void canStartDelayWhenDelayIsAlreadyStarted() {
        delayManager.startDelay(A_DELAY);
        delayManager.startDelay(A_DELAY);

        assertTrue(delayManager.isRunning());
    }

    @Test
    public void canCancelDelayEvenIfDelayIsAlreadyCanceled() {
        delayManager.startDelay(A_DELAY);
        delayManager.cancelDelay();
        delayManager.cancelDelay();

        assertFalse(delayManager.isRunning());
    }

}
