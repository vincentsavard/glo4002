package ca.ulaval.glo4002.devices;

public class Siren {

    private boolean activated;

    public Siren() {
        activated = false;
    }

    public void activate() {
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    // For test purposes only
    protected boolean isRinging() {
        return activated;
    }

}
