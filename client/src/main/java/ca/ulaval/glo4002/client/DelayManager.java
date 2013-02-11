package ca.ulaval.glo4002.client;

import java.util.Timer;
import java.util.TimerTask;

public class DelayManager {

  private Timer timer;
  private DelayResponder responder;
  private Object identifier;

  public DelayManager(DelayResponder responder) {
    this.responder = responder;
  }

  public void startDelay(int delay, Object identifier) {
    if (timer != null) {
      timer.cancel();
      timer = null;
    }
    timer = new Timer();
    timer.schedule(new TimerAction(), delay * 1000);
  }

  private class TimerAction extends TimerTask {
    @Override
    public void run() {
      responder.delayExpired(identifier);
      timer.cancel();
      timer = null;
    }
  }

  public void cancelDelay() {
    if (timer != null) {
      timer.cancel();
      timer = null;
      identifier = null;
    }
  }

  public boolean isRunning() {

    return timer != null;
  }
}
