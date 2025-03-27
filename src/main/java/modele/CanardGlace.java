package modele;

import combat.CapaciteSpecialeGlace;

public class CanardGlace extends Canard {

    public CanardGlace(String nom, int pv, int pa) {
        super(nom, TypeCanard.GLACE, pv, pa, new CapaciteSpecialeGlace());
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
