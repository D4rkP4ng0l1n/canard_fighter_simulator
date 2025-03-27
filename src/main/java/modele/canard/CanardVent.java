package modele.canard;

import combat.capaciteSpeciale.CapaciteSpecialeVent;

public class CanardVent extends Canard {

    public CanardVent(String nom, int pv, int pa) {
        super(nom, TypeCanard.VENT, pv, pa, new CapaciteSpecialeVent());
    }

    public void activerCapaciteSpeciale(Canard canardCible) {
        if (this.getPointsAttaque() < 15) {
            throw new IllegalArgumentException(
                    "Le canard n'a pas assez de points d'attaque pour activer la capacité spéciale !");
        }
        this.getCapaciteSpeciale().activer(canardCible);
        this.setPointsAttaque(this.getPointsAttaque() - 15);
    }

}
