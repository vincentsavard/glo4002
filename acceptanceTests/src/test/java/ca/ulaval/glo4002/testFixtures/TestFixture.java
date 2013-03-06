package ca.ulaval.glo4002.testFixtures;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
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
    private static final String AN_ADDRESS = "123 fausse rue";
    private static final String RAPID_PIN = "#0";
    private static final String WRONG_PIN = "2222";
    private static final int THIRTY_TWO_SECONDS_IN_MILLISECONDS = 32000;
    private static final int THIRTY_SECONDS_IN_MILLISECONDS = 30000;

    private CentralServer centralServer;
    private EmergencyServer emergencyServer;
    private AlarmSystem alarmSystem;
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
        keypad = new Keypad(alarmSystem);
        alarmSystem.setReady();
    }

    public void initializeAlarmSystem() {
        alarmSystem.registerToCentralServer(AN_ADDRESS);
    }

    public void armSystem() {
        alarmSystem.armWithoutDelay();
    }

    public void openMainDoor() {
        startTime = System.currentTimeMillis();
        mainDoorIntrusionPolicy = new MainDoorIntrusionPolicy(alarmSystem);
        mainDoorDetector = new Detector(mainDoorIntrusionPolicy);
        mainDoorDetector.trigger();
    }

    public void openSecondaryDoor() {
        intrusionPolicy = new IntrusionPolicy(alarmSystem);
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
        intrusionPolicy = new IntrusionPolicy(alarmSystem);
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

    public void setReceivedCallToFalse() {
        EmergencyServer.called = false;
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

}