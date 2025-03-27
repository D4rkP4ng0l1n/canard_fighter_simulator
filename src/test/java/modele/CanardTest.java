package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CanardTest {

    private CanardEau canardEau;
    private CanardFeu canardFeu;
    private CanardVent canardVent;
    private CanardGlace canardGlace;

    @BeforeEach
    public void setUp() {
        canardEau = new CanardEau("Eau", 100, 50);
        canardFeu = new CanardFeu("Feu", 100, 50);
        canardVent = new CanardVent("Vent", 100, 50);
        canardGlace = new CanardGlace("Glace", 100, 50);
    }

    @AfterEach
    public void tearDown() {
        canardEau = null;
        canardFeu = null;
        canardVent = null;
        canardGlace = null;
    }

    @Test
    public void testCreationCanardNomKO() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CanardEau(null, 100, 50).getClass();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CanardEau("", 100, 50).getClass();
        });
    }

    @Test
    public void testCreationCanardPdvNegatifOuNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CanardEau("Eau", 0, 50).getClass();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CanardEau("Eau", -100, 50).getClass();
        });
    }

    @Test
    public void testCreationCanardPaNegatifOuNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CanardEau("Eau", 100, 0).getClass();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CanardEau("Eau", 100, -50).getClass();
        });
    }

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
