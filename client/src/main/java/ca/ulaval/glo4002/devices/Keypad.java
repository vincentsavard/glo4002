package ca.ulaval.glo4002.devices;

public class Keypad {

    private int validPIN = 12345;
    private AlarmSystem alarmSystem;

	public Keypad(AlarmSystem alarmSystem) {
	    this.alarmSystem = alarmSystem;
    }

	public void armSystem(int submittedPIN) throws BadStateException, InvalidPINException {
		validatePIN(submittedPIN);
		alarmSystem.arm();
    }
	
	private void validatePIN(int submittedPIN) throws InvalidPINException {
		if (submittedPIN != validPIN) {
			throw new InvalidPINException();
		}
    }

	public void disarmSystem(int submittedPIN) throws InvalidPINException {
		validatePIN(submittedPIN);
	    alarmSystem.disarm();
    }

	public void changePIN(int submittedPIN, int newPIN) throws InvalidPINException {
		validatePIN(submittedPIN);
		validPIN = newPIN;
    }

	protected boolean isPINValid(int submittedPIN) {
	    return validPIN == submittedPIN;
    }
}
