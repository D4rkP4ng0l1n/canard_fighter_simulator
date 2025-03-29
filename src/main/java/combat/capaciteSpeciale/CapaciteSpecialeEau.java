package combat.capaciteSpeciale;

import combat.Statut;
import modele.canard.Canard;

public class CapaciteSpecialeEau implements CapaciteSpeciale {

    // Capacite Spéciale de type EAU : Retire les problèmes de statut du Canard
    // Actif, le soigne de la moitié des PV restants de la cible et inflige des
    // dégâts équilavent à la moitié de ce soin

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        canardActif.setStatut(Statut.NEUTRE);
        int soin = canardCible.getPointsDeVieCombat() / 2;
        canardActif.soigner(soin);
        canardCible.subirDegats(soin / 2);
    }

    @Override
    public String toString() {
        return "Capacité Spéciale Eau : Soigne de la moitié des PV restants de la cible et inflige des dégâts équivalents à la moitié de ce soin.";
    }

}
