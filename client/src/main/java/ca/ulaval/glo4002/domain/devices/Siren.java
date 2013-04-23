package ca.ulaval.glo4002.domain.devices;

public class Siren {

    private boolean activated = false;

    public void activate() {
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    // For test purposes only
    public boolean isRinging() {
        return activated;
    }

}
