package combat.capaciteSpeciale;

import combat.Statut;
import combat.capacite.Effet;
import modele.canard.Canard;

public class CapaciteSpecialeFeu implements CapaciteSpeciale {

    // Capacité Spéciale de type FEU : Inflige des dégâts équivalents à 10% des PV
    // max du Canard Cible, augmente l'attaque du canard Actif et brule
    // le Canard Cible

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        canardCible.subirDegats((int) (canardCible.getPointsDeVieCombat() * 0.1));
        if (canardCible.getStatut() != Statut.BRULURE) {
            canardCible.retirerStatut();
            canardCible.appliquerEffetStatut(Statut.BRULURE);
        }
        canardActif.appliquerEffet(Effet.BOOST_ATTAQUE, 1.5);
    }

}
