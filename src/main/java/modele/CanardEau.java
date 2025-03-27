package modele;

import combat.capaciteSpeciale.CapaciteSpecialeEau;

public class CanardEau extends Canard {

    public CanardEau(String nom, int pv, int pa) {
        super(nom, TypeCanard.EAU, pv, pa, new CapaciteSpecialeEau());
    }

    public void activerCapaciteSpeciale() {
        if (this.getPointsAttaque() < 15) {
            throw new IllegalArgumentException(
                    "Le canard n'a pas assez de points d'attaque pour activer la capacité spéciale !");
        }
        this.getCapaciteSpeciale().activer(this);
        this.setPointsAttaque(this.getPointsAttaque() - 15);
    }

}
