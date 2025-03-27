package combat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import combat.capacite.Capacite;
import modele.canard.Canard;
import modele.canard.CanardEau;
import modele.canard.CanardFeu;
import modele.canard.CanardGlace;
import modele.canard.CanardVent;
import modele.canard.TypeCanard;

public class CapaciteSpecialeTest {

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

    @Test
    public void testCapaciteSpecialeEau() {
        int initialPV = canardEau.getPointsDeVie();
        canardFeu.attaquer(canardEau, Capacite.FEU_TEST);
        assertEquals(
                initialPV
                        - canardFeu.getPointsAttaqueCombat() * Combat.COEFF_EQUILIBRAGE * Capacite.FEU_TEST.getDegats()
                                * TypeCanard.getMultiplicateur(canardFeu.getType(), canardEau.getType()),
                canardEau.getPointsDeVieCombat());
        canardEau.utiliserCapaciteSpeciale(canardFeu);
        assertFalse(canardEau.capaciteSpecialeDisponible());
        assertEquals(initialPV, canardEau.getPointsDeVieCombat());
        assertEquals(75, canardFeu.getPointsDeVieCombat());
    }

    @Test
    public void testCapaciteSpecialeFeu() {
        int initialPV = canardEau.getPointsDeVie();
        canardFeu.utiliserCapaciteSpeciale(canardEau);
        assertFalse(canardFeu.capaciteSpecialeDisponible());
        assertEquals(canardEau.getStatut(), Statut.BRULURE);
        assertEquals(initialPV * 0.9, canardEau.getPointsDeVieCombat());
        // Ajouter le test sur le boost d'attaque apres capacite speciale
    }

    @Test
    public void testCapaciteSpecialeVent() {
        // A implémenter après avoir mis en place le système de BOOST
    }

    @Test
    public void testCapaciteSpecialeGlace() {
        int initialPV = canardEau.getPointsDeVie();
        canardGlace.utiliserCapaciteSpeciale(canardEau);
        assertFalse(canardGlace.capaciteSpecialeDisponible());
        assertEquals(canardEau.getStatut(), Statut.GEL);
        assertEquals(initialPV * 0.8, canardEau.getPointsDeVieCombat());
    }
}