package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CanardTest {

    private Canard canard = new Canard("Canard");

    @Test
    public void getNom() {
        assertEquals("Canard", canard.getNom());
    }
}
