package modele;

import combat.CapaciteSpecialeGlace;

public class CanardGlace extends Canard {

    public CanardGlace(String nom, int pv, int pa) {
        super(nom, TypeCanard.GLACE, pv, pa, new CapaciteSpecialeGlace());
    }

}
