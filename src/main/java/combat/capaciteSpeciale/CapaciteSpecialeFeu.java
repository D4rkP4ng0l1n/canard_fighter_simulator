package combat.capaciteSpeciale;

import combat.Statut;
import combat.capacite.Effet;
import modele.canard.Canard;

public class CapaciteSpecialeFeu implements CapaciteSpeciale {

    private static final float COEFF_EQUILIBRAGE = 0.1f; // Coefficient d'équilibrage pour les dégâts infligés
    private static final float COEFF_BOOST_ATTAQUE = 1.5f; // Coefficient de boost d'attaque

    // Capacité Spéciale de type FEU : Inflige des dégâts équivalents à
    // COEFF_EQUILIBRAGE des PV max du Canard Cible,
    // augmente l'attaque du canard Actif et brule le Canard Cible

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        System.out.println((canardActif.getNom() + " UTILISE SA CAPACITE SPECIALE !"));
        canardCible.subirDegats((int) (canardCible.getPointsDeVieCombat() * COEFF_EQUILIBRAGE));
        if (canardCible.getStatut() != Statut.BRULURE) {
            canardCible.retirerStatut();
            canardCible.appliquerEffetStatut(Statut.BRULURE);
        }
        canardActif.appliquerEffet(Effet.BOOST_ATTAQUE, COEFF_BOOST_ATTAQUE);
    }

    @Override
    public String toString() {
        return "Capacité Spéciale Feu : Inflige des dégâts équivalents à " + COEFF_EQUILIBRAGE * 100
                + "% des PV max du Canard Cible, augmente l'attaque du canard Actif et brule le Canard Cible.";
    }
}
