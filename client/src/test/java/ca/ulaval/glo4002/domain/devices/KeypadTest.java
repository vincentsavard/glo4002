package ca.ulaval.glo4002.domain.devices;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;
import ca.ulaval.glo4002.domain.devices.InvalidPINException;
import ca.ulaval.glo4002.domain.devices.Keypad;

public class KeypadTest {

    private static final String NEW_PIN = "98765";
    private static final String VALID_PIN = "12345";
    private static final String INVALID_PIN = "31584";

    private Keypad keypad;

    @Mock
    private AlarmSystem alarmSystem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        keypad = new Keypad(alarmSystem);

        doReturn(true).when(alarmSystem).validatePIN(VALID_PIN);
    }

    @Test
    public void canArmSystemWhenPINIsValid() {
        keypad.armSystem(VALID_PIN);
        verify(alarmSystem).armWithThirtySecondsDelay();
    }

    @Test
    public void canDisarmSystemWhenPINIsValid() {
        keypad.disarmSystem(VALID_PIN);
        verify(alarmSystem).disarm();
    }

    @Test
    public void canSendRequestToChangePIN() {
        keypad.requestPINChange(VALID_PIN, NEW_PIN);
        verify(alarmSystem).changePIN(VALID_PIN, NEW_PIN);
    }

    @Test(expected = InvalidPINException.class)
    public void whenArmSystemIfPINIsInvalidThenAnInvalidPINExceptionIsThrown() {
        keypad.armSystem(INVALID_PIN);
    }

    @Test(expected = InvalidPINException.class)
    public void whenDisarmSystemIfPINIsInvalidThenAnInvalidPINExceptionIsThrown() {
        keypad.disarmSystem(INVALID_PIN);
    }

}
