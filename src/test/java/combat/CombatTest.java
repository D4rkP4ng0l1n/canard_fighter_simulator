package combat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.canard.Canard;
import modele.canard.CanardEau;
import modele.canard.CanardFeu;

public class CombatTest {

    private Combat combat;
    private Canard canardEau, canardFeu;

    @BeforeEach
    public void setUp() {
        canardEau = new CanardEau("Eau", 100, 50, 29);
        canardFeu = new CanardFeu("Feu", 100, 50, 30);
        combat = new Combat(canardEau, canardFeu);
    }

    @AfterEach
    public void tearDown() {
        canardEau = null;
        canardFeu = null;
        combat = null;
    }

    @Test
    public void testCreationCombat() {
        assertEquals(canardFeu, combat.getAttaquant());
    }
}
