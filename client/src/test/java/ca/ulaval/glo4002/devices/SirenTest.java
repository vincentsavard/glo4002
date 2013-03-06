package ca.ulaval.glo4002.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SirenTest {

    private Siren siren;

    @Before
    public void setUp() {
        siren = new Siren();
    }

    @Test
    public void whenInitializedSirenDoesNotRing() {
        assertFalse(siren.isRinging());
    }

    @Test
    public void whenSirenActivatedThenItIsRinging() {
        siren.activate();
        assertTrue(siren.isRinging());
    }

    @Test
    public void givenAnActivatedSirenWhenDeactivatedThenDoesNotRing() {
        siren.activate();
        siren.deactivate();
        assertFalse(siren.isRinging());
    }

}
