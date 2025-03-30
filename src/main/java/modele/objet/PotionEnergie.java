package modele.objet;

import modele.canard.Canard;

public class PotionEnergie extends Objet {

    private int pointsEnergie;

    public PotionEnergie(int pointsEnergie) {
        super("Potion d'Énergie", "Restaure " + pointsEnergie + " points d'énergie.");
        this.pointsEnergie = pointsEnergie;
    }

    @Override
    public void utiliser(Canard canard) {
        int energieAvant = canard.getEnergie();
        canard.regenererEnergie(pointsEnergie);
        int energieApres = canard.getEnergie();
        System.out.println(canard.getNom() + " a récupéré " + (energieApres - energieAvant) + " points d'énergie !");
    }
}