package modele.objet;

import modele.canard.Canard;

public class PotionSoin extends Objet {

    private int pointsDeSoin;

    public PotionSoin(int pointsDeSoin) {
        super("Potion de Soin", "Restaure " + pointsDeSoin + " points de vie.");
        this.pointsDeSoin = pointsDeSoin;
    }

    @Override
    public void utiliser(Canard canard) {
        int pvAvant = canard.getPointsDeVieCombat();
        canard.soigner(pointsDeSoin);
        int pvApres = canard.getPointsDeVieCombat();
        System.out.println(canard.getNom() + " a été soigné de " + (pvApres - pvAvant) + " PV !");
    }
}