package ca.ulaval.glo4002.domain.alarmSystem;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.ulaval.glo4002.core.timer.DelayTimer;
import ca.ulaval.glo4002.domain.devices.BadStateException;

public class AlarmSystemTest {

    private static final int DELAY_IN_SECONDS_BEFORE_ARMING = 30;

    @Mock
    private DelayTimer delayTimer;

    @InjectMocks
    private AlarmSystem alarmSystem;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        doAnswer(new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                alarmSystem.delayExpired();
                return null;
            }

        }).when(delayTimer).startDelay(anyInt());
    }

    @Test
    public void whenSystemIsCreatedThenSystemIsDisarmed() {
        assertFalse(alarmSystem.isArmed());
    }

    @Test
    public void whenMethodArmIsCalledThenUnarmedSystemIsArmed() {
        alarmSystem.armWithThirtySecondsDelay();
        assertTrue(alarmSystem.isArmed());
    }

    @Test
    public void whenMethodDisarmedIsCalledThenArmedSystemIsDisarmed() {
        alarmSystem.armWithThirtySecondsDelay();
        alarmSystem.disarm();
        assertFalse(alarmSystem.isArmed());
    }

    @Test(expected = BadStateException.class)
    public void whenMethodArmIsCalledSystemNotReadyThrowsException() {
        alarmSystem.setNotReady();
        alarmSystem.armWithThirtySecondsDelay();
    }

    @Test
    public void whenSystemSetNotReadyAndSetReadyThenCanBeArmed() {
        alarmSystem.setNotReady();
        alarmSystem.setReady();

        alarmSystem.armWithThirtySecondsDelay();

        assertTrue(alarmSystem.isArmed());
    }

    @Test
    public void whenMethodStartDelayIsCalledTheDelayIsStarted() {
        alarmSystem.armWithThirtySecondsDelay();
        verify(delayTimer).startDelay(DELAY_IN_SECONDS_BEFORE_ARMING);
    }

    @Test
    public void whenDelayRunsOutTheSystemIsArmed() {
        alarmSystem.armWithThirtySecondsDelay();
        assertTrue(alarmSystem.isArmed());
    }

    @Test
    public void whenArmingSystemIfSystemIsDisarmedBeforeDelayExpiredThenItIsStillDisarmedAfterDelay() {
        AlarmSystem alarmSystemWithDelay = new AlarmSystem();

        alarmSystemWithDelay.armWithThirtySecondsDelay();
        alarmSystemWithDelay.disarm();
        alarmSystemWithDelay.delayExpired();

        assertFalse(alarmSystemWithDelay.isArmed());
    }

}
