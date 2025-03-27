package combat;

import modele.Canard;

public class CapaciteSpecialeGlace implements CapaciteSpeciale {

    @Override
    public void activer(Canard canardCible) {
        if (canardCible.getPointsAttaque() < 10) {
            throw new IllegalArgumentException(
                    "Le canard cible n'a pas assez de points d'attaque pour activer la capacité spéciale !");
        }
        // Effet de la capacite
    }

}
