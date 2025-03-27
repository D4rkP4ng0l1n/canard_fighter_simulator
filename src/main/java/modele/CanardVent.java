package modele;

import combat.CapaciteSpecialeVent;

public class CanardVent extends Canard {

    public CanardVent(String nom, int pv, int pa) {
        super(nom, TypeCanard.VENT, pv, pa, new CapaciteSpecialeVent());
    }

}
