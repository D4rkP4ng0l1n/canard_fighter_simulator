package combat.capaciteSpeciale;

import java.util.Random;

import modele.canard.Canard;

public class CapaciteSpecialeSol implements CapaciteSpeciale {

    private static final int COEFF_EQUILIBRAGE = 4; // Coefficient d'équilibrage pour les dégâts infligés

    // Capacité Spéciale de type SOL : Une chance sur 4 de mettre le Canard adverse
    // KO

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        System.out.println((canardActif.getNom() + " UTILISE SA CAPACITE SPECIALE !"));
        Random random = new Random();
        int chance = random.nextInt(COEFF_EQUILIBRAGE);
        if (chance == 0) {
            canardCible.subirDegats(canardCible.getPointsDeVieCombat());
            System.out.println("OHKO !");
        }

    }

    @Override
    public String toString() {
        return "Capacité Spéciale SOL : 1/" + COEFF_EQUILIBRAGE + " chances de mettre le Canard Cible KO.";
    }

}
