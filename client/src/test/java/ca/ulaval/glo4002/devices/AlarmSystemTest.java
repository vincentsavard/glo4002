package ca.ulaval.glo4002.devices;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.ulaval.glo4002.devices.AlarmSystem;
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
    		public Object answer(InvocationOnMock invocation) throws Throwable {
    			alarmSystem.delayExpired(invocation.getArguments()[1]);
    			return null;
    		}
    	}).when(delayTimer).startDelay(anyInt(), any());
    }
    
    @Test
    public void systemIsDisarmedWhenCreated() {
    	assertFalse(alarmSystem.isArmed());
    }
    
    @Test
    public void unArmedSystemIsArmedWhenMethodArmIsCalled() throws BadStateException {    	
    	alarmSystem.arm();
    	assertTrue(alarmSystem.isArmed());
    }
    
    @Test
    public void armedSystemIsDisarmedWhenMethodDisarmedIsCalled() throws BadStateException {
    	alarmSystem.arm();
    	alarmSystem.disarm();
    	assertFalse(alarmSystem.isArmed());
    }
    
    @Test(expected = BadStateException.class)
    public void systemNotReadyThrowsExceptionWhenMethodArmIsCalled() throws BadStateException{
    	alarmSystem.setNotReady();
    	alarmSystem.arm();
    }
    
    @Test
    public void systemSetNotReadyAndSetReadyCanBeArmed() throws BadStateException{
    	alarmSystem.setNotReady();
    	alarmSystem.setReady();
    	alarmSystem.arm();
    	assertTrue(alarmSystem.isArmed());
    }
    
    @Test
    public void whenMethodStartDelayIsCalledTheDelayIsStarted() throws BadStateException {
    	alarmSystem.arm();
    	verify(delayTimer).startDelay(DELAY_IN_SECOND_BEFORE_ARMING, alarmSystem);
    }
    
    @Test
    public void theSystemIsArmedWhenDelayRunsOut() throws BadStateException {
    	alarmSystem.arm();
    	assertTrue(alarmSystem.isArmed());
    }
    
    @Test
    public void whenArmingSystemIfSystemIsDisarmedBeforeDelayExpiredItIsStillDisarmedAfterDelay() throws BadStateException {
    	AlarmSystem alarmSystemWithDelay = new AlarmSystem();
    	alarmSystemWithDelay.arm();
    	alarmSystemWithDelay.disarm();
    	alarmSystemWithDelay.delayExpired(alarmSystemWithDelay);
    	assertFalse(alarmSystemWithDelay.isArmed());
    }
}
