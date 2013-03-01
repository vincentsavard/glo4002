package ca.ulaval.glo4002.utilities;

import java.util.Timer;
import java.util.TimerTask;

public class DelayTimer {

    private static final int MILLISECONDS_PER_SECOND = 1000;

    private Timer timer;
    private DelayTimerDelegate delayTimerDelegate;

    public DelayTimer(final DelayTimerDelegate delayTimerDelegate) {
        this.delayTimerDelegate = delayTimerDelegate;
    }

    public void startDelay(final int delayInSeconds) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        timer.schedule(new TimerAction(), delayInSeconds * MILLISECONDS_PER_SECOND);
    }

    public void cancelDelay() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public boolean isRunning() {
        return timer != null;
    }

    private class TimerAction extends TimerTask {

        @Override
        public void run() {
            delayTimerDelegate.delayExpired();
            timer.cancel();
            timer = null;
        }
    }
}
