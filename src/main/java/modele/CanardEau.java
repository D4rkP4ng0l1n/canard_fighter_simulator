package modele;

import combat.CapaciteSpecialeEau;

public class CanardEau extends Canard {

    public CanardEau(String nom, int pv, int pa) {
        super(nom, TypeCanard.EAU, pv, pa, new CapaciteSpecialeEau());
    }

}
