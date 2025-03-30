package combat.capaciteSpeciale;

import combat.Statut;
import modele.canard.Canard;

public class CapaciteSpecialeToxique implements CapaciteSpeciale {

    private static final float COEFF_EQUILIBRAGE = 0.25f; // Coefficient d'équilibrage pour les dégâts infligés

    // Capacité Spéciale de type TOXIQUE : Empoisonne la cible
    // Inflige des dégâts équivalent à 25% des PV MAX de la cible

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        System.out.println((canardActif.getNom() + " UTILISE SA CAPACITE SPECIALE !"));
        canardCible.subirDegats((int) (canardCible.getPointsDeVieCombat() * COEFF_EQUILIBRAGE));
        if (canardCible.getStatut() != Statut.EMPOISONNEMENT) {
            canardCible.retirerStatut();
            canardCible.appliquerEffetStatut(Statut.EMPOISONNEMENT);
        }
    }

    @Override
    public String toString() {
        return "Capacité Spéciale Toxique : Inflige des dégâts équivalents à " + COEFF_EQUILIBRAGE * 100
                + "% des PV max du Canard Cible et l'empoisonne.";
    }

}
