package modele;

import combat.CapaciteSpecialeFeu;

public class CanardFeu extends Canard {

    public CanardFeu(String nom, int pv, int pa) {
        super(nom, TypeCanard.FEU, pv, pa, new CapaciteSpecialeFeu());
    }

}
