package combat.capaciteSpeciale;

import combat.capacite.Effet;
import modele.canard.Canard;

public class CapaciteSpecialeVent implements CapaciteSpeciale {

    private static final float COEFF_BOOST_VITESSE = 1.5f; // Coefficient de boost de vitesse
    private static final int BOOST_CRITIQUE = 0; // 100% de chance d'infliger un coup critique

    // Capacité Spéciale de type VENT : Augmente la vitesse du Canard Actif et sa
    // prochaine attaque a 100% de chance d'être un coup critique

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        System.out.println((canardActif.getNom() + " UTILISE SA CAPACITE SPECIALE !"));
        canardActif.appliquerEffet(Effet.BOOST_VITESSE, COEFF_BOOST_VITESSE);
        canardActif.appliquerEffet(Effet.CRIT, BOOST_CRITIQUE);
    }

    @Override
    public String toString() {
        return "Capacité Spéciale Vent : Augmente la vitesse du Canard Actif et sa prochaine attaque a 100% de chance d'être un coup critique.";
    }
}
