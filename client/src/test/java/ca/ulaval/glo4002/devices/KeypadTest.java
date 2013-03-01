package ca.ulaval.glo4002.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class KeypadTest {

    private static final String NEW_PIN = "98765";
    private static final String VALID_PIN = "12345";
    private static final String INVALID_PIN = "54321";
    private static final String FORBIDDEN_PIN = "A345";

    private Keypad keyPad;

    @Mock
    private AlarmSystem alarmSystem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        keyPad = new Keypad(alarmSystem);
    }

    @Test
    public void canArmSystemWhenPINIsValid() throws BadStateException, InvalidPINException {
        keyPad.armSystem(VALID_PIN);
        Mockito.verify(alarmSystem).arm();
    }

    @Test(expected = InvalidPINException.class)
    public void whenArmingSystemIfPINIsNotValidThrowAnException() throws InvalidPINException, BadStateException {
        keyPad.armSystem(INVALID_PIN);
    }

    @Test
    public void canDisarmSystemWhenPINIsValid() throws InvalidPINException {
        keyPad.disarmSystem(VALID_PIN);
        Mockito.verify(alarmSystem).disarm();
    }

    @Test(expected = InvalidPINException.class)
    public void whenDisarmingSystemIfPINIsNotValidThrowAnException() throws InvalidPINException {
        keyPad.disarmSystem(INVALID_PIN);
    }

    @Test
    public void newPINsetAsValidPINWhenPINIsValid() throws InvalidPINException {
        keyPad.changePIN(VALID_PIN, NEW_PIN);
        assertTrue(keyPad.isPINValid(NEW_PIN));
    }

    @Test(expected = InvalidPINException.class)
    public void whenChangingPINIfPINIsNotValidThrowAnException() throws InvalidPINException {
        keyPad.changePIN(INVALID_PIN, NEW_PIN);
    }

    @Test(expected = PINFormatForbiddenException.class)
    public void whenChangingPINIfNewPINIsNotOfTheRightFormatThrowAnException() throws PINFormatForbiddenException {
        keyPad.changePIN(VALID_PIN, FORBIDDEN_PIN);
    }
}
