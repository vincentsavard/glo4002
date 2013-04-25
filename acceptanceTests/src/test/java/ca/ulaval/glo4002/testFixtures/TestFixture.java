package ca.ulaval.glo4002.testFixtures;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.codehaus.jettison.json.JSONObject;

import ca.ulaval.glo4002.centralServer.domain.user.UserDirectoryLocator;
import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.core.communication.Communicator;
import ca.ulaval.glo4002.domain.alarmSystem.AlarmSystem;
import ca.ulaval.glo4002.domain.alarmSystem.PINValidator;
import ca.ulaval.glo4002.domain.devices.Detector;
import ca.ulaval.glo4002.domain.devices.Keypad;
import ca.ulaval.glo4002.domain.policies.FirePolicy;
import ca.ulaval.glo4002.domain.policies.IntrusionPolicy;
import ca.ulaval.glo4002.domain.policies.MainDoorIntrusionPolicy;
import ca.ulaval.glo4002.domain.policies.Policy;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

import com.jayway.awaitility.Awaitility;

public class TestFixture {

    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "#0";
    private static final String WRONG_PIN = "2222";
    private static final String NEW_PIN = "54321";
    private static final int THIRTY_TWO_SECONDS_IN_MILLISECONDS = 32000;
    private static final int THIRTY_SECONDS_IN_MILLISECONDS = 30000;
    private static final String AN_ADDRESS = "123 rue ville";
    private static final int A_ZONE = 10;

    private static final String ALARM_LOG_RESOURCE = "http://localhost:9001/alarm/";
    private static final String USER_ID = "1";
    private static final String ALARM_KEY = "alarms";

    private CentralServer centralServer;
    private EmergencyServer emergencyServer;
    private AlarmSystem alarmSystem;
    private Communicator communicator;
    private Keypad keypad;
    private Detector mainDoorDetector;
    private Detector secondaryDoorDetector;
    private Policy mainDoorIntrusionPolicy;
    private Policy intrusionPolicy;
    private Policy firePolicy;
    private Detector movementDetector;
    private Detector smokeDetector;
    private PINValidator validator;
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
        UserDirectoryLocator.getInstance().deleteDirectory();
    }

    public void createAlarmSystem() {
        alarmSystem = new AlarmSystem();
        communicator = new Communicator(AN_ADDRESS);
        validator = new PINValidator();
        keypad = new Keypad(alarmSystem, validator);
        alarmSystem.setReady();
    }

    public void armSystem() {
        alarmSystem.armWithoutDelay();
    }

    public void openMainDoor() {
        startTime = System.currentTimeMillis();
        mainDoorIntrusionPolicy = new MainDoorIntrusionPolicy(alarmSystem, communicator);
        mainDoorDetector = new Detector(mainDoorIntrusionPolicy, A_ZONE);
        mainDoorDetector.trigger();
    }

    public void openSecondaryDoor() {
        intrusionPolicy = new IntrusionPolicy(alarmSystem, communicator);
        secondaryDoorDetector = new Detector(intrusionPolicy, A_ZONE);
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

    public void disarmSystemWithGoodPIN() {
        keypad.disarmSystem(DEFAULT_PIN);
    }

    public void disarmSystemWithWrongPIN() {
        keypad.disarmSystem(WRONG_PIN);
    }

    public void triggerMovementDetector() {
        intrusionPolicy = new IntrusionPolicy(alarmSystem, communicator);
        movementDetector = new Detector(intrusionPolicy, A_ZONE);
        movementDetector.trigger();
    }

    public void verifyPoliceWasCalledAfterThirtySeconds() throws InterruptedException {
        Awaitility.setDefaultTimeout(THIRTY_TWO_SECONDS_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        Awaitility.await().until(emergenciesWereCalled());
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
        assertTrue(EmergencyServer.policeWasCalled);
    }

    public void verifyPoliceWasNotCalled() {
        assertFalse(EmergencyServer.policeWasCalled);
    }

    public void verifyAlarmLogIsEmpty() throws Exception {
        JSONObject log = getJSONAlarmLog();
        assertTrue(log.isNull(ALARM_KEY));
    }

    public void verifyAlarmLogIsNotEmpty() throws Exception {
        JSONObject log = getJSONAlarmLog();
        assertFalse(log.isNull(ALARM_KEY));
    }

    private JSONObject getJSONAlarmLog() throws Exception {
        URL url = new URL(ALARM_LOG_RESOURCE + USER_ID);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
        }

        return getJSONResponseFromServer(connection);
    }

    private JSONObject getJSONResponseFromServer(HttpURLConnection connection) throws Exception {
        StringBuilder builder = new StringBuilder();
        String currentLine = "";
        BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((currentLine = serverAnswer.readLine()) != null) {
            builder.append(currentLine);
        }

        connection.disconnect();

        String userJSONFormat = builder.toString();
        JSONObject user = new JSONObject(userJSONFormat);

        return user;
    }

    public void setReceivedCallToFalse() {
        EmergencyServer.policeWasCalled = false;
        EmergencyServer.fireFightersWereCalled = false;
    }

    public static Callable<Boolean> emergenciesWereCalled() {
        return new Callable<Boolean>() {

            public Boolean call() throws Exception {
                return EmergencyServer.policeWasCalled;
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
        assertTrue(validator.validatePIN(NEW_PIN));
    }

    public void requestPINChangeWithWrongPIN() {
        keypad.requestPINChange(WRONG_PIN, NEW_PIN);
    }

    public void verifyDefaultPINIsStillTheValidPIN() {
        assertTrue(validator.validatePIN(DEFAULT_PIN));
    }

    public void detectSmoke() {
        firePolicy = new FirePolicy(alarmSystem, communicator);
        smokeDetector = new Detector(firePolicy, A_ZONE);
        smokeDetector.trigger();
    }

    public void verifySirenIsOn() {
        assertTrue(alarmSystem.isSirenRinging());
    }

    public void verifyZoneWasTransmittedToCentral() {
        assertEquals(EmergencyServer.calledZone, A_ZONE);
    }

    public void firemenWereCalled() {
        assertTrue(EmergencyServer.fireFightersWereCalled);
    }

    public void requestPINChangeWithCurrentPIN() {
        validator.changePIN(DEFAULT_PIN, DEFAULT_PIN);
    }

    public void requestPINChangeWithPreviousPIN() {
        validator.changePIN(DEFAULT_PIN, NEW_PIN);
        validator.changePIN(NEW_PIN, DEFAULT_PIN);
    }

}