package ca.ulaval.glo4002.domain.alarmSystem;

import static org.junit.Assert.*;

import org.junit.Test;


public class PINValidatorTest {

    private static final String DEFAULT_PIN = "12345";
    private static final String VALID_PIN = "12345";
    private static final String RAPID_PIN = "#0";
    private static final String INVALID_PIN = "54321";
    private static final String TOO_SHORT_PIN = "123";
    private static final String TOO_LONG_PIN = "1235467";
    private static final String FORBIDDEN_PIN = "A345";
    private static final String LONG_NEW_PIN = "987654";
    private static final String SHORT_NEW_PIN = "1234";
    private PINValidator validator = new PINValidator();

    @Test
    public void whenSystemIsCreatedItValidatesDefaultPIN() {
        assertTrue(validator.validatePIN(DEFAULT_PIN));
    }

    @Test
    public void whenRapidPINIsGivenSystemValidatesRapidPIN() {
        assertTrue(validator.validatePIN(RAPID_PIN));
    }

    @Test
    public void whenAWrongPINIsGivenThenSystemDoesntValidatePIN() {
        assertFalse(validator.validatePIN(INVALID_PIN));
    }

    @Test(expected = RecentlyUsedPINException.class)
    public void whenChangingPINIfPINISCurrentPINThrowAnException() {
        validator.changePIN(VALID_PIN, VALID_PIN);
    }

    @Test(expected = RecentlyUsedPINException.class)
    public void whenChangingPINIfPINISRecentlyUsedPINThrowAnException() {
        validator.changePIN(VALID_PIN, SHORT_NEW_PIN);
        validator.changePIN(SHORT_NEW_PIN, VALID_PIN);
    }

    @Test(expected = PINFormatForbiddenException.class)
    public void whenChangingPINIfPINIsNotValidThrowAnException() {
        validator.changePIN(VALID_PIN, TOO_LONG_PIN);
    }

    @Test(expected = PINFormatForbiddenException.class)
    public void whenChangingPINIfPINIsTooShortThrowAnException() {
        validator.changePIN(VALID_PIN, TOO_SHORT_PIN);
    }

    @Test(expected = PINFormatForbiddenException.class)
    public void whenChangingPINIfPINIsTooLongThrowAnException() {
        validator.changePIN(VALID_PIN, TOO_LONG_PIN);
    }

    @Test(expected = InvalidPINException.class)
    public void whenChangingPINIfPINIsRapidPINThrowAnException() {
        validator.changePIN(RAPID_PIN, LONG_NEW_PIN);
    }

    @Test(expected = PINFormatForbiddenException.class)
    public void whenChangingPINIfNewPINIsNotValidThrowAnExecption() {
        validator.changePIN(VALID_PIN, FORBIDDEN_PIN);
    }

    @Test
    public void whenAPINOfSixNumbersChangeIsMadeNewPINisValidated() {
        validator.changePIN(VALID_PIN, LONG_NEW_PIN);
        assertTrue(validator.validatePIN(LONG_NEW_PIN));
    }

    @Test
    public void whenAPINOfFourNumbersChangeIsMadeNewPINisValidated() {
        validator.changePIN(VALID_PIN, SHORT_NEW_PIN);
        assertTrue(validator.validatePIN(SHORT_NEW_PIN));
    }

}
