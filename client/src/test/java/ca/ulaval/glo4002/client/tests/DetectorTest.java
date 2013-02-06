package ca.ulaval.glo4002.client.tests;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.ulaval.glo4002.client.Detector;
import ca.ulaval.glo4002.client.DetectorSignalHandler;

public class DetectorTest {

	@Test
	public void sendSignalToDetectorSignalHandlerWhenTriggered() {

		final DetectorSignalHandler detectorSignalHandlerMocked = mock(DetectorSignalHandler.class);
		Detector detector = new Detector() {

			@Override
			protected void initDetector() {
				detectorSignalHandler = detectorSignalHandlerMocked;
			}
		};
		detector.trigger();
		verify(detectorSignalHandlerMocked).treatSignal(detector);
	}

}
