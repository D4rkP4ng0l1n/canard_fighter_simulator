package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import combat.Combat;
import combat.capacite.Capacite;
import modele.canard.Canard;
import modele.canard.CanardEau;
import modele.canard.CanardFeu;
import modele.canard.CanardGlace;
import modele.canard.CanardVent;
import modele.canard.TypeCanard;

public class CanardTest {

    private Canard canardEau;
    private Canard canardFeu;
    private Canard canardVent;
    private Canard canardGlace;

    @BeforeEach
    public void setUp() {
        canardEau = new CanardEau("Eau", 100, 50, 30);
        canardFeu = new CanardFeu("Feu", 100, 50, 30);
        canardVent = new CanardVent("Vent", 100, 50, 30);
        canardGlace = new CanardGlace("Glace", 100, 50, 30);
    }

    @AfterEach
    public void tearDown() {
        canardEau = null;
        canardFeu = null;
        canardVent = null;
        canardGlace = null;
    }

    // ---------- TESTS GETTERS ---------- //

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

    @Test
    public void testGetVitesse() {
        assertEquals(30, canardEau.getVitesse());
        assertEquals(30, canardFeu.getVitesse());
        assertEquals(30, canardVent.getVitesse());
        assertEquals(30, canardGlace.getVitesse());
    }

    @Test
    public void testGetEnergie() {
        assertEquals(100, canardEau.getEnergie());
        assertEquals(100, canardFeu.getEnergie());
        assertEquals(100, canardVent.getEnergie());
        assertEquals(100, canardGlace.getEnergie());

    }

    // ---------- TESTS METHODES ---------- //

    @Test
    public void testAttaquerNeutre() {
        int initialPV = canardFeu.getPointsDeVie();
        canardVent.attaquer(canardFeu, Capacite.VENT_TEST);
        assertEquals(
                initialPV - canardVent.getPointsAttaqueCombat() * Combat.COEFF_EQUILIBRAGE
                        * Capacite.VENT_TEST.getDegats(),
                canardFeu.getPointsDeVieCombat());
    }

    @Test
    public void testAttaquerSuperEfficace() {
        int initialPV = canardFeu.getPointsDeVie();
        canardEau.attaquer(canardFeu, Capacite.EAU_TEST);
        assertTrue(
                initialPV - canardEau.getPointsAttaqueCombat() * Combat.COEFF_EQUILIBRAGE
                        * Capacite.EAU_TEST.getDegats() * 1.5 <= canardFeu
                                .getPointsDeVieCombat());

    }

    @Test
    public void testAttaquerPasTresEfficace() {
        int initialPV = canardFeu.getPointsDeVie();
        canardGlace.attaquer(canardFeu, Capacite.GLACE_TEST);
        assertEquals(
                initialPV - canardGlace.getPointsAttaqueCombat() * Combat.COEFF_EQUILIBRAGE
                        * Capacite.GLACE_TEST.getDegats() * 0.5,
                canardFeu.getPointsDeVieCombat());

    }

    @Test
    public void testEstKO() {
        canardEau.subirDegats(100);
        assertTrue(canardEau.estKO());
        canardFeu.subirDegats(50);
        assertFalse(canardFeu.estKO());
    }

    @Test
    public void testInitStatsEau() {
        Canard c = new CanardEau("Water");
        assertTrue(c.getPointsDeVie() >= 45 && c.getPointsDeVie() <= 50, c.toString());
        assertTrue(c.getPointsAttaque() >= 30 && c.getPointsAttaque() <= 35, c.toString());
        assertTrue(c.getVitesse() >= 40 && c.getVitesse() <= 45, c.toString());
    }

    @Test
    public void testInitStatsFeu() {
        Canard c = new CanardFeu("Fire");
        assertTrue(c.getPointsDeVie() >= 35 && c.getPointsDeVie() <= 40, c.toString());
        assertTrue(c.getPointsAttaque() >= 45 && c.getPointsAttaque() <= 55, c.toString());
        assertTrue(c.getVitesse() >= 35 && c.getVitesse() <= 45, c.toString());
    }

    @Test
    public void testInitStatsGlace() {
        Canard c = new CanardGlace("Ice");
        assertTrue(c.getPointsDeVie() >= 55 && c.getPointsDeVie() <= 60, c.toString());
        assertTrue(c.getPointsAttaque() >= 25 && c.getPointsAttaque() <= 30, c.toString());
        assertTrue(c.getVitesse() >= 30 && c.getVitesse() <= 35, c.toString());
    }

    @Test
    public void testInitStatsVent() {
        Canard c = new CanardVent("Wind");
        assertTrue(c.getPointsDeVie() >= 30 && c.getPointsDeVie() <= 35, c.toString());
        assertTrue(c.getPointsAttaque() >= 40 && c.getPointsAttaque() <= 45, c.toString());
        assertTrue(c.getVitesse() >= 45 && c.getVitesse() <= 50, c.toString());
    }
}