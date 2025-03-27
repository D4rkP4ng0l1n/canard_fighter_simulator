package combat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.CanardEau;
import modele.CanardFeu;
import modele.CanardGlace;
import modele.CanardVent;

public class CapaciteSpecialeTest {

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
    public void testCapaciteSpecialeEau() {
        canardEau.subirDegats(20);
        assertEquals(80, canardEau.getPointsDeVie());
        canardEau.activerCapaciteSpeciale();
        assertEquals(100, canardEau.getPointsDeVie());
    }

    @Test
    public void testCapaciteSpecialeFeu() {
        canardFeu.activerCapaciteSpeciale(canardEau);
        assertEquals(80, canardEau.getPointsDeVie());
        assertEquals(Statut.BRULURE, canardEau.getStatut());
    }

    @Test
    public void testCapaciteSpecialeVent() {
        canardVent.activerCapaciteSpeciale(canardEau);
        assertEquals(60, canardEau.getPointsDeVie());
    }

    @Test
    public void testCapaciteSpecialeGlace() {
        canardGlace.activerCapaciteSpeciale(canardEau);
        assertEquals(80, canardEau.getPointsDeVie());
        assertEquals(Statut.GEL, canardEau.getStatut());
    }

}
