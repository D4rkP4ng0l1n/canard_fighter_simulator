package combat;

import modele.canard.Canard;

public class Combat {

    private Canard canard1;
    private Canard canard2;
    private int tourActuel;

    public Combat(Canard canard1, Canard canard2) {
        this.canard1 = canard1;
        this.canard2 = canard2;
        this.tourActuel = 1;
    }

    public void lancerCombat() {
        while (!this.canard1.estKO() || !this.canard2.estKO()) {
            System.out.println("Tour " + this.tourActuel);
            tourDeCombat(this.canard1, this.canard2);
            this.tourActuel++;
        }
    }

    public void tourDeCombat(Canard attaquant, Canard defenseur) {

    }
}
