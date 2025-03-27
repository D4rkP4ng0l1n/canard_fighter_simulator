package modele;

import combat.CapaciteSpecialeFeu;

public class CanardFeu extends Canard {

    public CanardFeu(String nom, int pv, int pa) {
        super(nom, TypeCanard.FEU, pv, pa, new CapaciteSpecialeFeu());
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
