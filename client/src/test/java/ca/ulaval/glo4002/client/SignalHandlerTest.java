package ca.ulaval.glo4002.client;

public class SignalHandlerTest {
    /*
     * //FIXME transférer ces tests
     * 
     * @Mock private CommunicationUnit communicationUnit;
     * 
     * @Mock private SystemState systemState;
     * 
     * @Mock private DelayTimer delayManager;
     * 
     * @Mock private MessageEncoder messageEncoder;
     * 
     * @Mock private Signal signal;
     * 
     * @InjectMocks private SignalHandler signalHandler;
     * 
     * private final int A_DELAY = 10; private final int A_ZERO_DELAY = 0;
     * private final String ANY_STRING = "a very common string"; private final
     * DetectorType A_DETECTOR_TYPE = Signal.DetectorType.MAIN_DOOR;
     * 
     * @Before public void setUp() { MockitoAnnotations.initMocks(this); }
     * 
     * @SuppressWarnings("unchecked")
     * 
     * @Test public void systemSendsRequestToCentralServer() {
     * doReturn(A_DETECTOR_TYPE).when(signal).getDetectorType();
     * doReturn(ANY_STRING).when(messageEncoder).generateEncodedMessage(
     * (HashMap<String, String>) any());
     * 
     * signalHandler.sendRequestToCentralServer(signal);
     * 
     * verify(communicationUnit).sendPostRequest(ANY_STRING); }
     * 
     * @Test public void timerIsStartedWhenSignalNeedsDelay() {
     * doReturn(A_DELAY).when(signal).getDelayToContactEmergency();
     * signalHandler.treatSignal(signal); verify(delayManager).startDelay(10,
     * signal); }
     * 
     * @Test public void systemSendsRequestToServerDirectlyWhenThereIsNoDelay()
     * { doReturn(A_ZERO_DELAY).when(signal).getDelayToContactEmergency();
     * doReturn(A_DETECTOR_TYPE).when(signal).getDetectorType();
     * signalHandler.treatSignal(signal);
     * verify(communicationUnit).sendPostRequest(anyString()); }
     * 
     * @Test public void
     * systemSendsRequestToServerWhenSignalNeedsDelayAndDelayIsExpired() {
     * doReturn(A_DELAY).when(signal).getDelayToContactEmergency();
     * doReturn(A_DETECTOR_TYPE).when(signal).getDetectorType(); doAnswer(new
     * Answer<Object>() {
     * 
     * @Override public Object answer(InvocationOnMock invocation) throws
     * Throwable { signalHandler.delayExpired(invocation.getArguments()[1]);
     * return signal; } }).when(delayManager).startDelay(A_DELAY, signal);
     * 
     * signalHandler.treatSignal(signal);
     * 
     * verify(communicationUnit).sendPostRequest(anyString()); }
     */
}
