package combat.capaciteSpeciale;

import combat.Statut;
import modele.canard.Canard;

public class CapaciteSpecialeGlace implements CapaciteSpeciale {

    private static final float COEFF_EQUILIBRAGE = 0.2f; // Coefficient d'équilibrage pour les dégâts infligés

    // Capacité Spéciale de type GLACE : Inflige des dégâts équivalents à
    // COEFF_EQUILIBRAGE des PV du canard Cible et le gèle

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        System.out.println((canardActif.getNom() + " UTILISE SA CAPACITE SPECIALE !"));
        canardCible.subirDegats((int) (canardCible.getPointsDeVieCombat() * COEFF_EQUILIBRAGE));
        if (canardCible.getStatut() != Statut.GEL) {
            canardCible.retirerStatut();
            canardCible.appliquerEffetStatut(Statut.GEL);
        }
    }

    @Override
    public String toString() {
        return "Capacité Spéciale Glace : Inflige des dégâts équivalents à " + COEFF_EQUILIBRAGE * 100
                + "% des PV max du Canard Cible et le gèle.";
    }

}
