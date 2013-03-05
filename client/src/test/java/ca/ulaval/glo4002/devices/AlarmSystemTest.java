package ca.ulaval.glo4002.devices;

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

import ca.ulaval.glo4002.utilities.DelayTimer;

public class AlarmSystemTest {

    private static final int DELAY_IN_SECOND_BEFORE_ARMING = 30;

    @Mock
    private DelayTimer delayTimer;

    @InjectMocks
    private AlarmSystem alarmSystem;

    @Before
    public void setUp() {
        alarmSystem = new AlarmSystem();
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
    public void systemIsDisarmedWhenCreated() {
        assertFalse(alarmSystem.isArmed());
    }

    @Test
    public void unArmedSystemIsArmedWhenMethodArmIsCalled() throws BadStateException {
        alarmSystem.armWithThirtySecondsDelay();
        assertTrue(alarmSystem.isArmed());
    }

    @Test
    public void armedSystemIsDisarmedWhenMethodDisarmedIsCalled() throws BadStateException {
        alarmSystem.armWithThirtySecondsDelay();
        alarmSystem.disarm();
        assertFalse(alarmSystem.isArmed());
    }

    @Test(expected = BadStateException.class)
    public void systemNotReadyThrowsExceptionWhenMethodArmIsCalled() throws BadStateException {
        alarmSystem.setNotReady();
        alarmSystem.armWithThirtySecondsDelay();
    }

    @Test
    public void systemSetNotReadyAndSetReadyCanBeArmed() throws BadStateException {
        alarmSystem.setNotReady();
        alarmSystem.setReady();
        alarmSystem.armWithThirtySecondsDelay();
        assertTrue(alarmSystem.isArmed());
    }

    @Test
    public void whenMethodStartDelayIsCalledTheDelayIsStarted() throws BadStateException {
        alarmSystem.armWithThirtySecondsDelay();
        verify(delayTimer).startDelay(DELAY_IN_SECOND_BEFORE_ARMING);
    }

    @Test
    public void theSystemIsArmedWhenDelayRunsOut() throws BadStateException {
        alarmSystem.armWithThirtySecondsDelay();
        assertTrue(alarmSystem.isArmed());
    }

    @Test
    public void whenArmingSystemIfSystemIsDisarmedBeforeDelayExpiredItIsStillDisarmedAfterDelay() throws BadStateException {
        AlarmSystem alarmSystemWithDelay = new AlarmSystem();
        alarmSystemWithDelay.armWithThirtySecondsDelay();
        alarmSystemWithDelay.disarm();
        alarmSystemWithDelay.delayExpired();
        assertFalse(alarmSystemWithDelay.isArmed());
    }
}
