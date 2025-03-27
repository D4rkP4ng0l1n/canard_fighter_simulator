package combat.capaciteSpeciale;

import combat.capacite.Effet;
import modele.canard.Canard;

public class CapaciteSpecialeVent implements CapaciteSpeciale {

    // Capacité Spéciale de type VENT : Augmente la vitesse du Canard Actif et sa
    // prochaine attaque a 100% de chance d'être un coup critique

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        canardActif.appliquerEffet(Effet.BOOST_VITESSE, 1.5);
        canardActif.appliquerEffet(Effet.CRIT, 10);
    }
}
