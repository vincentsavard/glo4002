package ca.ulaval.glo4002.client;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.ulaval.glo4002.client.Signal.DetectorType;

public class SoftwareDetectorAdapterTest {

    private DetectorType A_DETECTOR_TYPE = DetectorType.MAIN_DOOR;
    private int A_DELAY = 10;

    @Test
    public void sendSignalToDetectorSignalHandlerWhenTriggered() {

        SignalHandler detectorSignalHandlerMocked = mock(SignalHandler.class);
        DetectorAdapter signalAdapter = new SoftwareDetectorAdapter(
                detectorSignalHandlerMocked);

        signalAdapter.sendSignalToSignalHandler(A_DETECTOR_TYPE, A_DELAY);

        verify(detectorSignalHandlerMocked).treatSignal((Signal) any());
    }

}
