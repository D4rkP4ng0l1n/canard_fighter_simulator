package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CanardTest {

    private CanardEau canardEau = new CanardEau("Eau", 100, 50);
    private CanardFeu canardFeu = new CanardFeu("Feu", 100, 50);
    private CanardVent canardVent = new CanardVent("Vent", 100, 50);
    private CanardGlace canardGlace = new CanardGlace("Glace", 100, 50);

    @Test
    public void testGetNom() {
        assertEquals("Eau", canardEau.getNom());
        assertEquals("Feu", canardFeu.getNom());
        assertEquals("Vent", canardVent.getNom());
        assertEquals("Glace", canardGlace.getNom());
    }

    @Test
    public void testGetType() {
        assertEquals(TypeCanard.EAU, canardEau.getType());
        assertEquals(TypeCanard.FEU, canardFeu.getType());
        assertEquals(TypeCanard.VENT, canardVent.getType());
        assertEquals(TypeCanard.GLACE, canardGlace.getType());
    }

    @Test
    public void testGetPointsDeVie() {
        assertEquals(100, canardEau.getPointsDeVie());
        assertEquals(100, canardFeu.getPointsDeVie());
        assertEquals(100, canardVent.getPointsDeVie());
        assertEquals(100, canardGlace.getPointsDeVie());
    }

    @Test
    public void testGetPointsAttaque() {
        assertEquals(50, canardEau.getPointsAttaque());
        assertEquals(50, canardFeu.getPointsAttaque());
        assertEquals(50, canardVent.getPointsAttaque());
        assertEquals(50, canardGlace.getPointsAttaque());
    }
}
