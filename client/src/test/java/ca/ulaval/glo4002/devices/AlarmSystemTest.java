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

    private static final int DELAY_IN_SECONDS_BEFORE_ARMING = 30;
    private static final String DEFAULT_PIN = "12345";
    private static final String VALID_PIN = "12345";
    private static final String RAPID_PIN = "#0";
    private static final String INVALID_PIN = "54321";
    private static final String FORBIDDEN_PIN = "A345";
    private static final String NEW_PIN = "98765";

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
    public void whenSystemIsCreatedItValidatesDefaultPIN() {
        assertTrue(alarmSystem.validatePIN(DEFAULT_PIN));
    }

    @Test
    public void whenRapidPINIsGivenSystemValidatesRapidPIN() {
        assertTrue(alarmSystem.validatePIN(RAPID_PIN));
    }

    @Test
    public void whenAWrongPINIsGivenThenSystemDoesntValidatePIN() {
        assertFalse(alarmSystem.validatePIN(INVALID_PIN));
    }

    @Test(expected = InvalidPINException.class)
    public void whenChangingPINIfPINIsNotValidThrowAnException() {
        alarmSystem.changePIN(INVALID_PIN, NEW_PIN);
    }

    @Test(expected = InvalidPINException.class)
    public void whenChangingPINIfPINIsRapidPINThrowAnException() {
        alarmSystem.changePIN(RAPID_PIN, NEW_PIN);
    }

    @Test(expected = PINFormatForbiddenException.class)
    public void whenChangingPINIfNewPINIsNotValidThrowAnExecption() {
        alarmSystem.changePIN(VALID_PIN, FORBIDDEN_PIN);
    }

    @Test
    public void whenAValidPINChangeIsMadeNewPINisValidated() {
        alarmSystem.changePIN(VALID_PIN, NEW_PIN);
        assertTrue(alarmSystem.validatePIN(NEW_PIN));
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
