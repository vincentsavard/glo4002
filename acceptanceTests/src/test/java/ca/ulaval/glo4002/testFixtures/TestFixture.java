package ca.ulaval.glo4002.testFixtures;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.communication.Communicator;
import ca.ulaval.glo4002.devices.AlarmSystem;
import ca.ulaval.glo4002.devices.Detector;
import ca.ulaval.glo4002.devices.Keypad;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;
import ca.ulaval.glo4002.policies.IntrusionPolicy;
import ca.ulaval.glo4002.policies.MainDoorIntrusionPolicy;
import ca.ulaval.glo4002.policies.Policy;

import com.jayway.awaitility.Awaitility;

public class TestFixture {

    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "#0";
    private static final String WRONG_PIN = "2222";
    private static final String NEW_PIN = "54321";
    private static final int THIRTY_TWO_SECONDS_IN_MILLISECONDS = 32000;
    private static final int THIRTY_SECONDS_IN_MILLISECONDS = 30000;
    private static final String AN_ADDRESS = "123 rue ville";
    private static final String EMPTY_LOG_NOTICE = "There is no alarms for this user.";
    private static final String LOG_INTRUSION_NOTICE = "INTRUSION";
    private static final String LOG_FIRE_NOTICE = "FIRE";
    // FIXME
    private static final String ALARM_LOG_RESOURCE = "http://localhost/alarm/";

    private CentralServer centralServer;
    private EmergencyServer emergencyServer;
    private AlarmSystem alarmSystem;
    private Communicator communicator;
    private Keypad keypad;
    private Detector mainDoorDetector;
    private Detector secondaryDoorDetector;
    private Policy mainDoorIntrusionPolicy;
    private Policy intrusionPolicy;
    private Detector movementDetector;
    private long startTime;

    public void initServers() throws Exception {
        centralServer = new CentralServer();
        emergencyServer = new EmergencyServer();
        centralServer.startServer();
        emergencyServer.startServer();
    }

    public void stopServers() throws Exception {
        centralServer.stopServer();
        emergencyServer.stopServer();
    }

    public void createAlarmSystem() {
        alarmSystem = new AlarmSystem();
        communicator = new Communicator(AN_ADDRESS);
        keypad = new Keypad(alarmSystem);
        alarmSystem.setReady();
    }

    public void armSystem() {
        alarmSystem.armWithoutDelay();
    }

    public void openMainDoor() {
        startTime = System.currentTimeMillis();
        mainDoorIntrusionPolicy = new MainDoorIntrusionPolicy(alarmSystem, communicator);
        mainDoorDetector = new Detector(mainDoorIntrusionPolicy);
        mainDoorDetector.trigger();
    }

    public void openSecondaryDoor() {
        intrusionPolicy = new IntrusionPolicy(alarmSystem, communicator);
        secondaryDoorDetector = new Detector(intrusionPolicy);
        secondaryDoorDetector.trigger();
    }

    public void armSystemWithFastPIN() {
        startTime = System.currentTimeMillis();
        keypad.armSystem(RAPID_PIN);
    }

    public void verifyAlarmSystemIsArmed() {
        assertTrue(alarmSystem.isArmed() || alarmSystem.isInTheProcessOfBeingArmed());
    }

    public void armSystemWithDefaultPIN() {
        startTime = System.currentTimeMillis();
        keypad.armSystem(DEFAULT_PIN);
    }

    public void armSystemWithWrongPIN() {
        keypad.armSystem(WRONG_PIN);
    }

    public void verifyAlarmSystemIsNotArmed() {
        assertFalse(alarmSystem.isArmed());
    }

    public void disarmSystemWithGoodNIP() {
        keypad.disarmSystem(DEFAULT_PIN);
    }

    public void disarmSystemWithWrongPIN() {
        keypad.disarmSystem(WRONG_PIN);
    }

    public void triggerMovementDetector() {
        intrusionPolicy = new IntrusionPolicy(alarmSystem, communicator);
        movementDetector = new Detector(intrusionPolicy);
        movementDetector.trigger();
    }

    public void verifyPoliceWasCalledAfterThirtySeconds() throws InterruptedException {
        Awaitility.setDefaultTimeout(THIRTY_TWO_SECONDS_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        Awaitility.await().until(emergencyServerWasCalled());
        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime >= THIRTY_SECONDS_IN_MILLISECONDS);
        verifyPoliceWasCalled();
    }

    public void verifyAlarmSystemWaitsThirtySecondsBeforeArming() throws InterruptedException {
        Awaitility.setDefaultTimeout(THIRTY_TWO_SECONDS_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        Awaitility.await().until(alarmSystemIsArmed());
        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime >= THIRTY_SECONDS_IN_MILLISECONDS);
        verifyAlarmSystemIsArmed();
    }

    public void verifyPoliceWasCalled() {
        assertTrue(EmergencyServer.called);
    }

    public void verifyPoliceWasNotCalled() {
        assertFalse(EmergencyServer.called);
    }

    public void verifyAlarmLogIsEmpty() {
        String log = getAlarmLog();
        assertTrue(log.contains(EMPTY_LOG_NOTICE));
    }

    public void verifyAlarmLogContainsIntrusionAlarm() {
        String log = getAlarmLog();
        assertTrue(log.contains(LOG_INTRUSION_NOTICE));
    }

    public void verifyAlarmLogContainsFireAlarm() {
        String log = getAlarmLog();
        assertTrue(log.contains(LOG_FIRE_NOTICE));
    }

    public void setReceivedCallToFalse() {
        EmergencyServer.called = false;
    }

    private String getAlarmLog() {
        String log = "";
        // TODO
        return log;
    }

    public static Callable<Boolean> emergencyServerWasCalled() {
        return new Callable<Boolean>() {

            public Boolean call() throws Exception {
                return EmergencyServer.called;
            }

        };
    }

    public Callable<Boolean> alarmSystemIsArmed() {
        return new Callable<Boolean>() {

            public Boolean call() throws Exception {
                return alarmSystem.isArmed();
            }

        };
    }

    public void requestPINChangeWithDefaultPIN() {
        keypad.requestPINChange(DEFAULT_PIN, NEW_PIN);
    }

    public void verifyDefaultPINHasBeenChangedForNewPIN() {
        assertTrue(alarmSystem.validatePIN(NEW_PIN));
    }

    public void requestPINChangeWithWrongPIN() {
        keypad.requestPINChange(WRONG_PIN, NEW_PIN);
    }

    public void verifyDefaultPINIsStillTheValidPIN() {
        assertTrue(alarmSystem.validatePIN(DEFAULT_PIN));
    }

}