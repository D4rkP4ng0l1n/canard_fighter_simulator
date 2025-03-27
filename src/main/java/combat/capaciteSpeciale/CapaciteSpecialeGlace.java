package combat.capaciteSpeciale;

import combat.Statut;
import modele.canard.Canard;

public class CapaciteSpecialeGlace implements CapaciteSpeciale {

    // Capacité Spéciale de type GLACE : Inflige des dégâts équivalents à 20% des PV
    // du canard Cible et le gèle

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        canardCible.subirDegats((int) (canardCible.getPointsDeVieCombat() * 0.2));
        if (canardCible.getStatut() != Statut.GEL) {
            canardCible.retirerStatut();
            canardCible.appliquerEffetStatut(Statut.GEL);
        }
    }

}
