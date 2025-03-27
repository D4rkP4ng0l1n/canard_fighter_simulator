package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CanardTest {

    private Canard canard = new Canard("Canard", TypeCanard.VENT, 100, 50);

    @Test
    public void testGetNom() {
        assertEquals("Canard", canard.getNom());
    }

    @Test
    public void testGetType() {
        assertEquals(TypeCanard.VENT, canard.getType());
    }

    @Test
    public void testGetPointsDeVie() {
        assertEquals(100, canard.getPointsDeVie());
    }

    @Test
    public void testGetPointsAttaque() {
        assertEquals(50, canard.getPointsAttaque());
    }
}
